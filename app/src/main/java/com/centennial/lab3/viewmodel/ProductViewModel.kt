package com.centennial.lab3.viewmodel

import androidx.lifecycle.*
import com.centennial.lab3.data.Product
import com.centennial.lab3.data.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    val allProducts: LiveData<List<Product>> = repository.allProducts.asLiveData()
    val favoriteProducts: LiveData<List<Product>> = repository.favoriteProducts.asLiveData()

    fun insert(product: Product) = viewModelScope.launch {
        repository.insert(product)
    }

    fun update(product: Product) = viewModelScope.launch {
        repository.update(product)
    }

    fun delete(product: Product) = viewModelScope.launch {
        repository.delete(product)
    }
}