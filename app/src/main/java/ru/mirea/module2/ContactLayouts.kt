package ru.mirea.module2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Contact(
    val name: String,
    val patronymic: String,
    val surname: String,
    val phone: String?,
    val address: String
)

private val contacts = listOf(
    Contact("Евгений", "Андреевич", "Лукашин", "+7 495 495 95 95",
        "г. Москва, 3-я улица Строителей, д. 25, кв. 12"),
    Contact("Василий", "Егорович", "Кузякин", null,
        "Ивановская область, дер. Крутово, д. 4"),
    Contact("Людмила", "Прокофьевна", "Калугина", "+7 495 788 78 78",
        "Москва, Большая Никитская, д. 43, кв. 290")
)

@Composable
fun ContactColumn(
    contact: Contact,
    modifier: Modifier = Modifier,
    alignment: Alignment.Horizontal = Alignment.Start
) {
    Column(modifier, horizontalAlignment = alignment) {
        val lines = listOf(
            "Имя: ${contact.name}",
            "Отчество: ${contact.patronymic}",
            "Фамилия: ${contact.surname}",
            "Мобильный телефон: ${contact.phone ?: "---"}",
            "Адрес: ${contact.address}"
        )
        for (line in lines) {
            Text(line, fontSize = 14.sp, lineHeight = 16.sp)
        }
    }
}

@Composable
fun ContactList() {
    Column(
        Modifier.fillMaxWidth().padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        for (contact in contacts) {
            ContactColumn(
                contact,
                Modifier.fillMaxWidth().background(Color.LightGray).padding(8.dp),
                Alignment.CenterHorizontally
            )
        }
    }
}

@Composable
fun ContactRow(contact: Contact) {
    Row(
        Modifier.fillMaxWidth().padding(8.dp).background(Color.LightGray).padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ContactColumn(contact, Modifier.weight(1f), Alignment.CenterHorizontally)
        Spacer(Modifier.width(8.dp))
        Image(
            painter = painterResource(R.drawable.favorite_star),
            contentDescription = "Избранное",
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ContactLayouts() {
    var selected by rememberSaveable { mutableIntStateOf(0) }
    Column(Modifier.fillMaxSize()) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            for ((index, title) in listOf("Контакт", "Список", "Строка").withIndex()) {
                TextButton(onClick = { selected = index }) {
                    Text(title, fontWeight = if (selected == index) FontWeight.Bold else FontWeight.Normal)
                }
            }
        }
        Column(Modifier.fillMaxWidth().verticalScroll(rememberScrollState())) {
            when (selected) {
                0 -> ContactColumn(contacts.first(), Modifier.padding(8.dp))
                1 -> ContactList()
                2 -> ContactRow(contacts.first())
            }
        }
    }
}
