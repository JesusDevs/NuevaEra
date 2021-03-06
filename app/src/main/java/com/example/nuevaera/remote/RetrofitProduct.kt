package com.example.nuevaera.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProduct {

    companion object {
    val BASE_URL = "https://my-json-server.typicode.com/Himuravidal/FakeAPIdata/"
    fun getRetrofitInstance(): ProductAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ProductAPI::class.java)
    }
}
}