package com.nishit.learnk.util

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nishit.learnk.extension.LocalDateTimeTypeAdapter
import com.nishit.learnk.model.Transactions
import java.time.LocalDateTime

class DataReader(private val context: Context) {
    private val gson: Gson = GsonBuilder().registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeTypeAdapter().nullSafe()).create()


    fun readTransactionFromAsset(): Transactions {
        try {
            val jsonString = context.assets.open("data.json").bufferedReader().use { it.readText() }
            return gson.fromJson(jsonString, Transactions::class.java)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return Transactions()
    }
}