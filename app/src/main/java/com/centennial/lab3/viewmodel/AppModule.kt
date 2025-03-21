package com.centennial.lab3.viewmodel

import android.content.Context
import androidx.test.espresso.core.internal.deps.dagger.Module
import androidx.test.espresso.core.internal.deps.dagger.Provides
import com.centennial.lab3.data.ProductDao
import com.centennial.lab3.data.ProductDatabase
import com.centennial.lab3.data.ProductRepository

import javax.inject.Singleton
import kotlin.text.Typography.dagger

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): ProductDatabase {
        return ProductDatabase.getDatabase(context)
    }

    @Provides
    fun provideDao(database: ProductDatabase) = database.productDao()

    @Provides
    fun provideRepository(dao: ProductDao) = ProductRepository(dao)
}