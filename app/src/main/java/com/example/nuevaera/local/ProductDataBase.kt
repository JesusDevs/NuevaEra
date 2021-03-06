package com.example.nuevaera.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nuevaera.pojo.ProductDetail
import com.example.nuevaera.pojo.ProductoResponseItem

@Database(entities = [ProductoResponseItem::class, ProductDetail::class], version = 1)
abstract class ProductDataBase : RoomDatabase() {

    abstract fun getProductDao(): ProductDao

    companion object {
        @Volatile
        private var INSTANCE: ProductDataBase? = null

        fun getDataBase(context: Context): ProductDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductDataBase::class.java,
                    "bookDao"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
