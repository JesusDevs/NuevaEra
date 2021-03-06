package com.example.nuevaera.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nuevaera.pojo.ProductDetail
import com.example.nuevaera.pojo.ProductoResponseItem

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllProduct(list: List<ProductoResponseItem>)

    @Query("SELECT * FROM productos")
    fun getAllProductDataBase(): LiveData<List<ProductoResponseItem>>

    @Query("SELECT * FROM productos WHERE id = :id")
    fun getProductByID(id: String): LiveData<ProductoResponseItem>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOneProductDetails(detail: ProductDetail)


    @Query("SELECT * FROM  productDetail WHERE id=:id")
    fun getOneProductDetails(id: Int): LiveData<ProductDetail>

}