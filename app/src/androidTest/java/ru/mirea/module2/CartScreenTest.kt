package ru.mirea.module2

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class CartScreenTest {
    @get:Rule
    val compose = createComposeRule()

    @Test
    fun initialSumIs306() {
        compose.setContent { MaterialTheme { ShoppingCartScreen() } }
        compose.onNodeWithText("Товаров на сумму: 306 рублей").assertIsDisplayed()
        compose.onNodeWithText("Количество товаров: 3").assertIsDisplayed()
    }

    @Test
    fun removeButtonDisappearsWhenCartBecomesEmpty() {
        compose.setContent { MaterialTheme { ShoppingCartScreen() } }
        repeat(3) { compose.onNodeWithText("Удалить товар").performClick() }
        compose.onNodeWithText("Товаров на сумму: 0 рублей").assertIsDisplayed()
        compose.onNodeWithText("Удалить товар").assertDoesNotExist()
        compose.onNodeWithText("Добавить товар").performClick()
        compose.onNodeWithText("Количество товаров: 1").assertIsDisplayed()
        compose.onNodeWithText("Удалить товар").assertIsDisplayed()
    }

    @Test
    fun cartSurvivesStateRestoration() {
        val restoration = StateRestorationTester(compose)
        restoration.setContent { MaterialTheme { ShoppingCartScreen() } }
        compose.onNodeWithText("Удалить товар").performClick()
        compose.onNodeWithText("Товаров на сумму: 250 рублей").assertIsDisplayed()
        restoration.emulateSavedInstanceStateRestore()
        compose.onNodeWithText("Товаров на сумму: 250 рублей").assertIsDisplayed()
        compose.onNodeWithText("Количество товаров: 2").assertIsDisplayed()
    }

    @Test
    fun nullNameHasFallback() {
        compose.setContent { Hello(null) }
        compose.onNodeWithText("Имя не задано").assertIsDisplayed()
    }
}
