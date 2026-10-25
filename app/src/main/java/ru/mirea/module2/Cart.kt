package ru.mirea.module2

import java.io.Serializable

data class Product(val id: Int, val name: String, val price: Int) : Serializable

data class Cart(val products: List<Product>) : Serializable {
    val totalSum: Int get() = products.sumOf { it.price }
    val freeDelivery: Boolean get() = totalSum > 500

    fun addProduct(price: Int): Cart {
        val id = if (products.isEmpty()) 0 else products.last().id + 1
        val product = Product(id, "Товар #${id + 1}", price)
        return copy(products = products + product)
    }

    fun removeProduct(): Cart {
        return copy(products = products.dropLast(1))
    }
}

fun initialProducts(): List<Product> {
    return listOf(
        Product(0, "Товар #1", 100),
        Product(1, "Товар #2", 150),
        Product(2, "Товар #3", 56)
    )
}
