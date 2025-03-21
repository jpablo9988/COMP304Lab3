package com.centennial.lab3.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = false)
    val id: Int, // Should be between 101-999
    val name: String,
    val price: Double, // Must be positive
    val dateOfDelivery: Date, // Can't be before current date
    val category: String, // Dropdown values: Cell Phone, Electronics, Appliances, Media
    val isFavorite: Boolean = false // On/Off toggle
)
