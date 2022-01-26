package com.nishit.learnk

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nishit.learnk.model.Transactions
import com.nishit.learnk.util.DataHandler
import kotlinx.android.synthetic.main.transactions_layout.*
import java.time.LocalDate
import kotlin.streams.toList

class TransactionsActivity : AppCompatActivity() {

    private lateinit var dayTransactionAdapter: DayTransactionAdapter
    private val dataHandler: DataHandler = DataHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transactions_layout)
        val now = LocalDate.now()
        val thisMonthsTransaction = getMonthsTransaction(now)

        dayTransactionAdapter = DayTransactionAdapter(this, thisMonthsTransaction, now.month, now.year)

        fabAddTransaction.setOnClickListener {
            val intent = Intent(this, AddTransactionActivity::class.java)
            startActivity(intent)
        }

        rvDayTransactions.adapter = dayTransactionAdapter
        rvDayTransactions.layoutManager = LinearLayoutManager(this)
    }

    private fun getMonthsTransaction(date: LocalDate): Transactions {
        val allTransactions = dataHandler.readTransactionFromAsset()
        val month = date.month
        val monthsTransactions =
            Transactions(allTransactions.transactions.stream().filter { transaction -> transaction.date.month == month }
                .toList())

        return monthsTransactions
    }
}