package com.centennial.lab3.viewmodel

import androidx.lifecycle.*
import com.centennial.lab3.data.ProductEntity
import com.centennial.lab3.data.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository)
    : ViewModel() {
    val allProducts: LiveData<List<ProductEntity>> = repository.allProducts.asLiveData()
    val favoriteProducts: LiveData<List<ProductEntity>> = repository.favoriteProducts.asLiveData()

    fun insert(productEntity: ProductEntity) = viewModelScope.launch {
        repository.insert(productEntity)
    }

    fun update(productEntity: ProductEntity) = viewModelScope.launch {
        repository.update(productEntity)
    }

    fun delete(productEntity: ProductEntity) = viewModelScope.launch {
        repository.delete(productEntity)
    }

}