package com.example.nuevaera.remote

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.nuevaera.local.ProductDao
import com.example.nuevaera.pojo.ProductDetail
import com.example.nuevaera.pojo.ProductoResponseItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductRepository(private val dao: ProductDao) {


    val liveDataProductDB: LiveData<List<ProductoResponseItem>> = dao.getAllProductDataBase()
    suspend fun getProductWithCorutines() {

        try {
            val response = RetrofitProduct.getRetrofitInstance().getProductAllItem()

            when (response.isSuccessful) {

                true -> response.body()?.let {
                    //Aca se esta insertando en la Base de datos
                    Log.d("repo1", "${it}")
                    dao.insertAllProduct(it)
                    Log.d("repo", "${it}")
                }
                false -> Log.d("ERROR", " ${response.code()} : ${response.errorBody()} ")
            }
        } catch (t: Throwable) {
            Log.e("ERROR CORUTINA", t.message.toString())
        }
    }
    suspend fun getDetailProductWithCourutines() {
        Log.d("REPOSITORY", "Utilizando corrutinas")
        try {
            val response = RetrofitProduct.getRetrofitInstance().getProductDetail()
            when (response.isSuccessful) {
                true -> response.body()?.let {
                    //aca se inserta en la base de datos
                    dao.insertOneProductDetails(it)
                }
                false -> Log.d("ERROR", "${response.code()}: ${response.errorBody()} ")
            }
        } catch (t: Throwable) {
            Log.e("ERROR CORRUTINA", t.message.toString())
        }
    }


    //metodo para traer detalle del producto selectionado por ID

    fun getProductDetail(id: Int):LiveData<ProductDetail>{
        return dao.getOneProductDetails(id)
    }


    //otra forma de repo corutinas
/*
    fun getProductDetailApi(id: Int) = CoroutineScope(Dispatchers.IO).launch {

        val service = kotlin.runCatching { RetrofitProduct.getRetrofitInstance().getProductDetail(id) }
        service.onSuccess {
            when(it.code()) {

                in 200..299 -> it.body()?.let { details ->
                    dao.insertOneProductDetails(details)
                    Log.e("detail", it.toString())
                }

                in 300..599 -> Log.e("ERROR", it.errorBody().toString())
                else -> Log.d("ERROR", it.errorBody().toString())

            }
        }

        service.onFailure {

            Log.e("ERROR", it.message.toString())

        }

    }*/


}
