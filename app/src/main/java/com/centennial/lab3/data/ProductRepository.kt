package com.centennial.lab3.data

import kotlinx.coroutines.flow.Flow

class ProductRepository(private val productDao: ProductDao) {
    val allProducts: Flow<List<Product>> = productDao.getAllProducts()
    val favoriteProducts: Flow<List<Product>> = productDao.getFavoriteProducts()

    suspend fun insert(product: Product) = productDao.insertProduct(product)
    suspend fun update(product: Product) = productDao.updateProduct(product)
    suspend fun delete(product: Product) = productDao.deleteProduct(product)
}