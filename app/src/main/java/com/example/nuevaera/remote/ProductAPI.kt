package com.example.nuevaera.remote

import com.example.nuevaera.pojo.ProductDetail
import com.example.nuevaera.pojo.ProductoResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductAPI {
    @GET("products/")
    suspend fun getProductAllItem(): Response<List<ProductoResponseItem>>

    @GET("details/")
    suspend fun getProductDetail() : Response<List<ProductDetail>>
}