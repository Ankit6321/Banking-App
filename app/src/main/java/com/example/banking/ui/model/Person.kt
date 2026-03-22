package com.example.banking.ui.model

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

data class Person(
    val name: String,
    val lastPayment: PaymentDetail,
    val color: Color = randomColor()
)

fun randomColor(): Color {
    val colors = listOf(
        Color(0xFFEF5350),
        Color(0xFFEC407A),
        Color(0xFFAB47BC),
        Color(0xFF7E57C2),
        Color(0xFF5C6BC0),
        Color(0xFF42A5F5),
        Color(0xFF26A69A),
        Color(0xFF66BB6A),
        Color(0xFFFFA726),
        Color(0xFF8D6E63)
    )
    return colors[Random.nextInt(colors.size)]
}
