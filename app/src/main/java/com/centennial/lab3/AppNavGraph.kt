package com.centennial.lab3

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.centennial.lab3.ui.screens.AddProductScreen
import com.centennial.lab3.ui.screens.EditProductScreen
import com.centennial.lab3.ui.screens.HomeScreen
import com.centennial.lab3.viewmodel.ProductViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavGraph(navController: NavHostController,
                viewModel: ProductViewModel) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Lab 3 - Product Manager with Room")
                }
                ,
                navigationIcon = {
                    if (navController.previousBackStackEntry != null) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back_button)
                            )
                        }
                    }
                }
            )
        }
        ,
        floatingActionButton = {

            FloatingActionButton(
                onClick = {
                    navController.navigate("add_product")
                },
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        },
    )
    {
        innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Home Screen - Displays the list of products
            composable("home") {
                HomeScreen(
                    navController = navController,
                    viewModel = viewModel
                )
            }
            composable("home/faves") {
                HomeScreen(
                    navController = navController,
                    viewModel = viewModel,
                    showFavorites = true
                )
            }
            // Add Product Screen - Allows user to add new products
            composable("add_product") {
                AddProductScreen(
                    navController = navController,
                    viewModel = viewModel
                )
            }
            // Edit Product Screen - Allows user to edit a product
            composable("edit_product/{productId}") { backStackEntry ->
                val productId = backStackEntry.arguments?.getString("productId")?.toIntOrNull() ?: 0
                EditProductScreen(
                    productId = productId,
                    viewModel = viewModel,
                    onNavigateBack = { navController.popBackStack() })
            }
        }
    }
}