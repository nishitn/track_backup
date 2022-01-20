package com.nishit.learnk

data class TransactionItem(
    val from: String,
    val to: String,
    val category: String,
    val note: String,
    val currency: String,
    val amount: Int
)