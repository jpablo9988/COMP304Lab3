package com.centennial.lab3.ui.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.centennial.lab3.viewmodel.ProductViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

import java.util.*

@SuppressLint("SimpleDateFormat")
@Composable
fun EditProductScreen(
    productId: Int, // Passed via navigation
    viewModel: ProductViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit // Function to navigate back after editing/deleting
) {
    val context = LocalContext.current
    val products by viewModel.allProducts.observeAsState(emptyList())

    // Find the product with the given ID
    val product = products.find { it.id == productId }

    var name by remember { mutableStateOf(product?.name ?: "") }
    var price by remember { mutableStateOf(product?.price?.toString() ?: "") }
    var category by remember { mutableStateOf(product?.category ?: "Cell Phone") }
    var isFavorite by remember { mutableStateOf(product?.isFavorite ?: false) }

    if (product == null) {
        Text("Product not found!", Modifier.padding(16.dp))
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Edit Product", style = MaterialTheme.typography.headlineSmall)

        // Name Input Field
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Product Name") },
            modifier = Modifier.fillMaxWidth()
        )

        // Price Input Field (Ensure it's a valid positive number)
        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Price") },
            modifier = Modifier.fillMaxWidth()
        )

        // Category Dropdown
        var expanded by remember { mutableStateOf(false) }
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(onClick = { expanded = true }) {
                Text(category)
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                listOf("Cell Phone", "Electronics", "Appliances", "Media").forEach { cat ->
                    DropdownMenuItem(onClick = {
                        category = cat
                        expanded = false
                    }, text = { Text(cat) })
                }
            }
        }

        // Favorite Toggle
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Switch(
                checked = isFavorite,
                onCheckedChange = {
                    isFavorite = it
                },
                thumbContent = if (isFavorite) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "An image of a black heart",
                            modifier = Modifier.size(SwitchDefaults.IconSize),
                        )
                    }
                } else {
                    null
                }
            )

        }
        // Save Button
        Button(
            onClick = {
                val newPrice = price.toDoubleOrNull()
                if (name.isNotBlank() && newPrice != null && newPrice > 0) {
                    val updatedProduct = product.copy(
                        name = name,
                        price = newPrice,
                        category = category,
                        isFavorite = isFavorite
                    )
                    viewModel.update(updatedProduct)
                    Toast.makeText(context, "Product updated!", Toast.LENGTH_SHORT).show()
                    onNavigateBack()
                } else {
                    Toast.makeText(context, "Invalid input!", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Changes")
        }

        // Delete Button
        Button(
            onClick = {
                viewModel.delete(product)
                Toast.makeText(context, "Product deleted!", Toast.LENGTH_SHORT).show()
                onNavigateBack()
            },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Delete Product")
        }
    }
}