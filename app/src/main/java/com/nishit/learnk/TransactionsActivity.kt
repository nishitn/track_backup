package com.nishit.learnk

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nishit.learnk.model.Transactions
import com.nishit.learnk.util.DataReader
import kotlinx.android.synthetic.main.transactions_layout.*
import java.time.LocalDate
import kotlin.streams.toList

class TransactionsActivity : AppCompatActivity() {

    private lateinit var dayTransactionAdapter: DayTransactionAdapter
    private val dataReader: DataReader = DataReader(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transactions_layout)
        val todaysDate = LocalDate.now()
        val thisMonthsTransaction = getMonthsTransaction(todaysDate)

        dayTransactionAdapter = DayTransactionAdapter(this, thisMonthsTransaction, todaysDate.month, todaysDate.year)

        fabAddTransaction.setOnClickListener {
            val intent = Intent(this, AddTransactionActivity::class.java)
            startActivity(intent)
        }

        rvDayTransactions.adapter = dayTransactionAdapter
        rvDayTransactions.layoutManager = LinearLayoutManager(this)
    }

    private fun getMonthsTransaction(date: LocalDate): Transactions {
        val allTransactions = dataReader.readTransactionFromAsset()
        val month = date.month
        val monthsTransactions =
            Transactions(allTransactions.transactions.stream().filter { transaction -> transaction.date.month == month }
                .toList())

        return monthsTransactions
    }
}