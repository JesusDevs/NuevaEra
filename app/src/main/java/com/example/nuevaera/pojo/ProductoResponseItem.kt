package com.example.nuevaera.pojo


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "productos")
data class ProductoResponseItem(
    @PrimaryKey
    @SerializedName("id")
    val id: Int = 10,
    @SerializedName("name")
    val name: String = "LG Q6 32GB",
    @SerializedName("price")
    val price: Int = 107760,
    @SerializedName("image")
    val image: String = "https://www.lg.com/us/images/cell-phones/md05902196/gallery/medium05.jpg"
)