package com.example.nuevaera.pojo


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "productDetail")
data class ProductDetail(
    @PrimaryKey
    @SerializedName("id")
    val id: Int = 1,
    @SerializedName("name")
    val name: String = "Samsung Galaxy A21s 64GB",
    @SerializedName("price")
    val price: Int = 167253,
    @SerializedName("image")
    val image: String = "https://images.samsung.com/is/image/samsung/es-galaxy-a21s-sm-a217fzkoeub-262755098?",
    @SerializedName("description")
    val description: String = "Tamaño 6,5'' Densidad 294 ppi Resolución de pantalla 720 x 1600",
    @SerializedName("lastPrice")
    val lastPrice: Int = 177253,
    @SerializedName("credit")
    val credit: Boolean = true
)