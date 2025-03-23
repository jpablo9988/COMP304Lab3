package com.centennial.lab3.data

import com.google.android.engage.food.datamodel.ProductEntity
import kotlinx.coroutines.flow.Flow

class ProductRepository(private val productDao: ProductDao) {
    val allProducts: Flow<List<ProductEntity>> = productDao.getAllProducts()
    val favoriteProducts: Flow<List<ProductEntity>> = productDao.getFavoriteProducts()

    suspend fun insert(productEntity: ProductEntity) = productDao.insertProduct(productEntity)
    suspend fun update(productEntity: ProductEntity) = productDao.updateProduct(productEntity)
    suspend fun delete(productEntity: ProductEntity) = productDao.deleteProduct(productEntity)
}