package com.centennial.lab3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.centennial.lab3.data.Product

@Composable
fun ProductItem(
    product: Product,
    onFavoriteToggle: (Product) -> Unit,
    onEditClick: (Product) -> Unit,
    onEdit: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = product.name, style = MaterialTheme.typography.titleMedium)
                Text(text = "Price: $${product.price}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Category: ${product.category}", style = MaterialTheme.typography.bodySmall)
            }

            // Favorite Toggle Button
            IconButton(onClick = { onFavoriteToggle(product) }) {
                Icon(
                    imageVector = if (product.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (product.isFavorite) Color.Red else Color.Gray
                )
            }

            // Edit Button
            IconButton(onClick = { onEditClick(product) }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Product",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}