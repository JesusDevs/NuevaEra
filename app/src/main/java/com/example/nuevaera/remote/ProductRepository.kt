package com.example.nuevaera.remote

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.nuevaera.local.ProductDao
import com.example.nuevaera.pojo.ProductoResponseItem

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
}
