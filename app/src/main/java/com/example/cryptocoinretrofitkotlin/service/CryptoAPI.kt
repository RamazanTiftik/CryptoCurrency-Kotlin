package com.example.cryptocoinretrofitkotlin.service

import io.reactivex.Observable
import com.example.cryptocoinretrofitkotlin.model.CryptoModel
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {

    // GET, POST, UPDATE, DELETE

    //https://raw.githubusercontent.com/  --> base url
    //atilsamancioglu/K21-JSONDataSet/master/crypto.json

    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getData(): Observable<List<CryptoModel>>

    //fun getData(): Call<List<CryptoModel>>

}