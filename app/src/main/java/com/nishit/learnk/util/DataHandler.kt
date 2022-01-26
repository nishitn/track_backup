package com.nishit.learnk.util

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nishit.learnk.extension.LocalDateTimeTypeAdapter
import com.nishit.learnk.model.Transactions
import java.time.LocalDateTime

class DataHandler(private val context: Context) {
    private val gson: Gson =
        GsonBuilder().registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeTypeAdapter().nullSafe()).create()


    fun readTransactionFromAsset(): Transactions {
        val jsonString = readFileToString("transactions.json")
        return gson.fromJson(jsonString, Transactions::class.java)
    }

    fun readEntityClassFromAsset(): Transactions {
        val jsonString = readFileToString("entityClass.json")
        return gson.fromJson(jsonString, Transactions::class.java)
    }

    fun readEntityFromAsset(): Transactions {
        val jsonString = readFileToString("entity.json")
        return gson.fromJson(jsonString, Transactions::class.java)
    }

    fun readChapterFromAsset(): Transactions {
        val jsonString = readFileToString("chapter.json")
        return gson.fromJson(jsonString, Transactions::class.java)
    }

    private fun readFileToString(fileName: String): String {
        try {
            return context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return ""
    }
}