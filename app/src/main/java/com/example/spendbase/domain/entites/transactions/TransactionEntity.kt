package com.example.spendbase.domain.entites.transactions

data class TransactionEntity(
    val startIconResource: Int,
    val title: String,
    val cardNumber: String,
    val status: TransactionStatusesEnum,
    val endIconResource: Int? = null,
    val amount: Int = 0
)