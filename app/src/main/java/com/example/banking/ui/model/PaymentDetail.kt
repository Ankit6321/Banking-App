package com.example.banking.ui.model

data class PaymentDetail(
    val amount: String,
    val date: String,
    val time: String,
    val status: PaymentStatus,
    val paymentDirection: PaymentDirection
)

enum class PaymentDirection {
    FROM, TO
}

enum class PaymentStatus {
    SUCCESS, FAILED, PENDING
}
