package com.nishit.learnk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nishit.learnk.model.Currency
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate
import java.time.Month

class MainActivity : AppCompatActivity() {

    private lateinit var dayTransactionAdapter: DayTransactionAdapter
    private val dayTransactionItems: MutableList<DayTransactionItem> = mutableListOf()
    private val transactionItems: MutableList<TransactionItem> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dayTransactionAdapter = DayTransactionAdapter(this, dayTransactionItems, transactionItems)

        val date = LocalDate.of(2022, Month.JUNE, 19)
        dayTransactionItems.add(DayTransactionItem(date, 2000, 5000))
        dayTransactionItems.add(DayTransactionItem(date.plusDays(1), 2000, 5000))
        dayTransactionItems.add(DayTransactionItem(date.plusDays(2), 2000, 5000))
        dayTransactionItems.add(DayTransactionItem(date.plusDays(3), 2000, 5000))
        dayTransactionItems.add(DayTransactionItem(date.plusDays(4), 2000, 5000))

        transactionItems.add(TransactionItem("Bank", "Hospital", "Bill", "Stitches", Currency.INR.symbol, 2000))
        transactionItems.add(TransactionItem("Bank", "Chemist", "Medicine", "Stitches", Currency.INR.symbol, 1000))
        transactionItems.add(TransactionItem("Bank", "Friends", "Eating Out", "Buffet", Currency.INR.symbol, 1500))

        rvDayTransactions.adapter = dayTransactionAdapter
        rvDayTransactions.layoutManager = LinearLayoutManager(this)

    }
}