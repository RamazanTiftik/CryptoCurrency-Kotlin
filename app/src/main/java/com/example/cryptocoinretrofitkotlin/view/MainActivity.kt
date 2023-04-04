package com.example.cryptocoinretrofitkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocoinretrofitkotlin.R
import com.example.cryptocoinretrofitkotlin.adapter.CryptoAdapter
import com.example.cryptocoinretrofitkotlin.databinding.ActivityMainBinding
import com.example.cryptocoinretrofitkotlin.model.CryptoModel
import com.example.cryptocoinretrofitkotlin.service.CryptoAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val BASE_URL="https://raw.githubusercontent.com/"
    private lateinit var cryptoModels: ArrayList<CryptoModel>
    private lateinit var cryptoAdapter: CryptoAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        //initialize
        compositeDisposable= CompositeDisposable()
        cryptoModels= ArrayList<CryptoModel>()

        loadData()

    }

    private fun loadData(){

        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CryptoAPI::class.java)

        compositeDisposable.add(retrofit.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse))

        /*
        val service=retrofit.create(CryptoAPI::class.java)
        val call=service.getData()

        call.enqueue(object: Callback<List<CryptoModel>>{
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {

                if (response.isSuccessful){
                    response.body()?.let {
                        cryptoModels=ArrayList(it)

                        for(cryptoModel: CryptoModel in cryptoModels){
                            //adapter
                            cryptoAdapter=CryptoAdapter(cryptoModels)
                            binding.recyclerView.layoutManager=LinearLayoutManager(this@MainActivity)
                            binding.recyclerView.adapter=cryptoAdapter
                        }

                    }
                }

            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })

         */

    }

    private fun handleResponse(cryptoList: List<CryptoModel>){

        cryptoModels=ArrayList(cryptoList)

        cryptoModels?.let {
            cryptoAdapter=CryptoAdapter(cryptoModels)
            binding.recyclerView.layoutManager=LinearLayoutManager(this@MainActivity)
            binding.recyclerView.adapter=cryptoAdapter
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

}