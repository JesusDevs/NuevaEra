package com.example.nuevaera.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nuevaera.local.ProductDataBase
import com.example.nuevaera.pojo.ProductoResponseItem
import com.example.nuevaera.remote.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application)  {

    private val repository : ProductRepository
    val productLiveDataFromDataBase: LiveData<List<ProductoResponseItem>>
    val ProductSelection = MutableLiveData<Int>()

    init {
        val dao = ProductDataBase.getDataBase(application).getProductDao()
        repository = ProductRepository(dao)
        viewModelScope.launch {
            repository.getProductWithCorutines()
        }
       productLiveDataFromDataBase = repository.liveDataProductDB
    }

}