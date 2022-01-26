package com.nishit.learnk.model

import com.google.gson.annotations.SerializedName

data class Transactions(
    @SerializedName("transactions") val transactions: List<Transaction> = listOf()
)