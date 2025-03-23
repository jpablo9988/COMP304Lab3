package com.centennial.lab3.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.centennial.lab3.ui.components.ProductItem
import com.centennial.lab3.viewmodel.ProductViewModel


@Composable
fun HomeScreen(viewModel: ProductViewModel,
               navController: NavHostController,
               showFavorites : Boolean = false) {
    val products by viewModel.allProducts.observeAsState(emptyList())
    val favProducts by viewModel.favoriteProducts.observeAsState(
        emptyList())
    Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
            Text(
                "List of Products: ", style = typography.titleLarge
            )
        Spacer(Modifier.size(30.dp))
        LazyColumn {
            val localProducts = if (showFavorites) favProducts else products;
            items(localProducts) { product ->
                ProductItem(
                    product,
                    onFavoriteToggle = {
                        product.isFavorite = !product.isFavorite;
                        viewModel.update(product);
                        if (showFavorites) navController.navigate("home/faves")
                        else navController.navigate("home")
                    },
                    onEditClick = { element: String ->
                        navController.navigate("edit_product/$element")
                    }
                )
            }
        }
        OutlinedButton(onClick = {
            if (showFavorites) navController.navigate("home");
            else navController.navigate("home/faves")
        })
        {
            Text(
                text = if (showFavorites) "Show All" else "Show Favorites",
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f))
        }
    }
}
