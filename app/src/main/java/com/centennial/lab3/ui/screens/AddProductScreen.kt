package com.centennial.lab3.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.centennial.lab3.R

import com.centennial.lab3.viewmodel.ProductViewModel
import com.google.android.engage.food.datamodel.ProductEntity
import java.util.Date

@Composable
fun AddProductScreen(
    viewModel: ProductViewModel,
    navController: NavHostController
) {
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    // --- Favorite States: ---- //
    var favoriteChecked by remember { mutableStateOf(true) }
    // --- Category Dropdown States: --- //
    val isDropDownExpanded = remember { mutableStateOf(false) }
    val itemPosition = remember { mutableIntStateOf(0) }
    val categoryList = listOf("Cell Phone", "Electronics", "Appliances", "Media");

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
        // __ Category Dropdown Menu: __ //
        Box {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    isDropDownExpanded.value = true
                }
            ) {
                OutlinedButton(onClick = { isDropDownExpanded.value = true }) {
                    Text(text = categoryList[itemPosition.intValue])
                }
            }
            DropdownMenu(
                expanded = isDropDownExpanded.value,
                onDismissRequest = {
                    isDropDownExpanded.value = false
                }) {
                categoryList.forEachIndexed { index, category ->
                    DropdownMenuItem(text = {
                        Text(text = category)
                    },
                        onClick = {
                            isDropDownExpanded.value = false
                            itemPosition.intValue = index
                        })
                }
            }
        }
        Switch(
            checked = favoriteChecked,
            onCheckedChange = {
                favoriteChecked = it
            },
            thumbContent = if (favoriteChecked) {
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

        Button(
            onClick = {
                if (name.isNotEmpty() && price.toDoubleOrNull() != null && price.toDouble() > 0) {
                    val productEntity = ProductEntity(
                        id = (101..999).random(),
                        name = name,
                        price = price.toDouble(),
                        dateOfDelivery = Date(),
                        category = categoryList[itemPosition.intValue],
                        isFavorite = favoriteChecked
                    )
                    viewModel.insert(productEntity)
                    navController.navigate("home");
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Add Product")
        }
    }
}
