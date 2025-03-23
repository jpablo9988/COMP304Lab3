package com.centennial.lab3

import ProductViewModelFactory
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.centennial.lab3.data.ProductDatabase
import com.centennial.lab3.data.ProductRepository
import com.centennial.lab3.ui.theme.JuanAmorocho_EvguenyAntsyferov_COMP304Sec001_Lab3Theme
import com.centennial.lab3.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val productViewModel by viewModels<ProductViewModel> {
        ProductViewModelFactory(
            ProductRepository(
                ProductDatabase.getDatabase(application).productDao()
            )
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JuanAmorocho_EvguenyAntsyferov_COMP304Sec001_Lab3Theme {

                val navController = rememberNavController();
                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNavGraph(navController = navController, productViewModel)
                }
            }
        }
    }
}