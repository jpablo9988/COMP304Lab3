package com.centennial.lab3.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.centennial.lab3.data.ProductEntity
import com.centennial.lab3.viewmodel.ProductViewModel
import java.util.Date

@Composable
fun AddProductScreen(
    viewModel: ProductViewModel = hiltViewModel(),
    navController: NavHostController
) {
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("Cell Phone") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Product Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Price") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (name.isNotEmpty() && price.toDoubleOrNull() != null && price.toDouble() > 0) {
                    val productEntity = ProductEntity(
                        id = (101..999).random(),
                        name = name,
                        price = price.toDouble(),
                        dateOfDelivery = Date(),
                        category = category
                    )
                    viewModel.insert(productEntity)
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Add Product")
        }
    }
}
