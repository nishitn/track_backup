package com.nishit.learnk.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.util.*

data class Transaction(
    @SerializedName("id") val id: String,
    @SerializedName("date") val date: LocalDateTime,
    @SerializedName("chapter") val chapter: String,
    @SerializedName("fromEntityClass") val fromEntityClass: String,
    @SerializedName("fromEntity") val fromEntity: String? = null,
    @SerializedName("toEntityClass") val toEntityClass: String,
    @SerializedName("toEntity") val toEntity: String? = null,
    @SerializedName("category") val category: String? = null,
    @SerializedName("note") val note: String? = null,
    @SerializedName("currency") val currency: String,
    @SerializedName("amount") val amount: Double,
    @SerializedName("tags") val tags: List<String>? = null,
    @SerializedName("transaction_type") val transactionType: String? = null,
    @SerializedName("links") val links: List<String>? = null
)