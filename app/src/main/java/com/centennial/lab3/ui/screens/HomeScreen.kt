package com.centennial.lab3.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.centennial.lab3.ui.components.ProductItem
import com.centennial.lab3.viewmodel.ProductViewModel


@Composable
fun HomeScreen(viewModel: ProductViewModel,
               navController: NavHostController) {
    val products by viewModel.allProducts.observeAsState(emptyList())
    Column(modifier = Modifier.padding(16.dp)) {
        Text("List of Products: ", style = typography.bodyLarge)
        LazyColumn {
        items(products) { product ->
            ProductItem(
                product,
                onFavoriteToggle = {
                    product.isFavorite = true;
                    viewModel.update(product);
                },
                onEditClick = {
                    element : String ->
                        navController.navigate("edit_product/$element")
                }
            )
        }
    }
}
}