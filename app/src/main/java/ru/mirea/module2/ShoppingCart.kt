package ru.mirea.module2

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Composable
fun ShoppingCartScreen() {
    var cart by rememberSaveable { mutableStateOf(Cart(initialProducts())) }
    val context = LocalContext.current

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Корзина", fontSize = 24.sp)
        Spacer(Modifier.height(16.dp))
        Column(Modifier.weight(1f).verticalScroll(rememberScrollState())) {
            if (cart.products.isEmpty()) {
                Text("Корзина пуста")
            }
            for (product in cart.products) {
                Text(
                    "${product.name} — ${product.price} рублей",
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
        Text("Количество товаров: ${cart.products.size}")
        Text("Товаров на сумму: ${cart.totalSum} рублей", fontSize = 18.sp)
        Spacer(Modifier.height(12.dp))
        AddProductSection {
            val updatedCart = cart.addProduct(Random.nextInt(0, 100))
            cart = updatedCart
            if (updatedCart.freeDelivery) {
                Toast.makeText(context, "Доставка бесплатная!", Toast.LENGTH_SHORT).show()
            }
        }
        RemoveProductSection(cart.products.isNotEmpty()) {
            cart = cart.removeProduct()
        }
    }
}

@Composable
fun AddProductSection(onAdd: () -> Unit) {
    Button(onClick = onAdd, modifier = Modifier.fillMaxWidth()) {
        Text("Добавить товар")
    }
}

@Composable
fun RemoveProductSection(hasProducts: Boolean, onRemove: () -> Unit) {
    if (hasProducts) {
        Button(onClick = onRemove, modifier = Modifier.fillMaxWidth()) {
            Text("Удалить товар")
        }
    }
}
