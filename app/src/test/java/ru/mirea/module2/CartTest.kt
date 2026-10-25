package ru.mirea.module2

import org.junit.Assert.*
import org.junit.Test
import java.io.*

class CartTest {
    @Test
    fun initialSumDoesNotGrowWhenReadAgain() {
        val cart = Cart(initialProducts())
        repeat(10) {
            assertEquals(306, cart.totalSum)
            assertEquals(3, cart.products.size)
        }
    }

    @Test
    fun addingProductUpdatesSumAndKeepsOriginalCart() {
        val cart = Cart(initialProducts())
        val updated = cart.addProduct(70)
        assertEquals(376, updated.totalSum)
        assertEquals(4, updated.products.size)
        assertEquals(Product(3, "Товар #4", 70), updated.products.last())
        assertEquals(306, cart.totalSum)
    }

    @Test
    fun removingProductRemovesLastItemOnly() {
        val updated = Cart(initialProducts()).removeProduct()
        assertEquals(250, updated.totalSum)
        assertEquals(listOf(0, 1), updated.products.map { it.id })
    }

    @Test
    fun emptyCartCanBeRemovedFromAndFilledAgain() {
        var cart = Cart(initialProducts())
        repeat(4) { cart = cart.removeProduct() }
        assertTrue(cart.products.isEmpty())
        assertEquals(0, cart.totalSum)
        assertEquals(Product(0, "Товар #1", 20), cart.addProduct(20).products.single())
    }

    @Test
    fun freeDeliveryStartsStrictlyAbove500() {
        val cart = Cart(listOf(Product(0, "Проверка", 500)))
        assertFalse(cart.freeDelivery)
        assertTrue(cart.addProduct(1).freeDelivery)
        assertFalse(cart.addProduct(1).removeProduct().freeDelivery)
    }

    @Test
    fun zeroPriceDoesNotChangeSum() {
        val updated = Cart(initialProducts()).addProduct(0)
        assertEquals(306, updated.totalSum)
        assertEquals(4, updated.products.size)
    }

    @Test
    fun savedCartRestoresAllProducts() {
        val cart = Cart(initialProducts()).addProduct(42)
        val bytes = ByteArrayOutputStream()
        ObjectOutputStream(bytes).use { it.writeObject(cart) }
        val restored = ObjectInputStream(ByteArrayInputStream(bytes.toByteArray())).use {
            it.readObject() as Cart
        }
        assertEquals(cart, restored)
        assertEquals(348, restored.totalSum)
    }
}
