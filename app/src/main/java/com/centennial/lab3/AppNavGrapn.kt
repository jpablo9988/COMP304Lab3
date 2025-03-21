package com.centennial.lab3

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.centennial.lab3.ui.screens.AddProductScreen
import com.centennial.lab3.ui.screens.EditProductScreen
import com.centennial.lab3.ui.screens.HomeScreen


@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        // Home Screen - Displays the list of products
        composable("home") {
            HomeScreen(navController = navController)
        }

        // Add Product Screen - Allows user to add new products
        composable("add_product") {
            AddProductScreen(navController = navController)
        }

        // Edit Product Screen - Allows user to edit a product
        composable("edit_product/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toIntOrNull() ?: 0
            EditProductScreen(productId = productId, onNavigateBack = { navController.popBackStack() })
        }
    }
}