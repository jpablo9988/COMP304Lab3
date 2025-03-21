package com.centennial.lab3.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.centennial.lab3.ui.components.ProductItem
import com.centennial.lab3.viewmodel.ProductViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(viewModel: ProductViewModel = hiltViewModel(), navController: NavHostController) {
    val products by viewModel.allProducts.observeAsState(emptyList())

    LazyColumn {
        items(products) { product ->
            ProductItem(
                product, onEdit = { /* Navigate to edit screen */ },
                onFavoriteToggle = TODO(),
                onEditClick = TODO()
            )
        }
    }
}