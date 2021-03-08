package com.example.nuevaera.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nuevaera.local.ProductDataBase
import com.example.nuevaera.pojo.ProductDetail
import com.example.nuevaera.pojo.ProductoResponseItem
import com.example.nuevaera.remote.ProductRepository
import kotlinx.coroutines.launch
import java.lang.reflect.InvocationTargetException

class ProductViewModel(application: Application) : AndroidViewModel(application)  {

    private val repository : ProductRepository
    val productLiveDataFromDataBase: LiveData<List<ProductoResponseItem>>
    val productSelection = MutableLiveData<ProductoResponseItem>()

    init {
        val dao = ProductDataBase.getDataBase(application).getProductDao()
        repository = ProductRepository(dao)
        viewModelScope.launch {
            repository.getProductWithCorutines()
            repository.getProductWithCorutines()
        }
       productLiveDataFromDataBase = repository.liveDataProductDB
    }

    //metodo para seleccionar el item y pasar a segundo fragmento con todos sus atributos (alternativa a bundle)
    fun selected(productosItem: ProductoResponseItem?) {
        productSelection.value = productosItem
    }
    //metodo para selecionar un item

    fun selectedItem() : LiveData<ProductoResponseItem> = productSelection


    fun getDetailProductoById(id :Int): LiveData<ProductDetail>{
        return repository.getProductDetail(id)

    }
}

