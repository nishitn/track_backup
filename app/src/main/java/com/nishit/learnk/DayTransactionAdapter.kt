package com.nishit.learnk

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nishit.learnk.model.Transactions
import kotlinx.android.synthetic.main.day_transaction_item.view.*
import java.time.LocalDate
import java.time.Month
import java.time.format.TextStyle
import java.util.*

class DayTransactionAdapter(
    private val activity: Activity,
    private val monthsTransaction: Transactions,
    private val month: Month,
    private val year: Int
) : RecyclerView.Adapter<DayTransactionAdapter.DayTransactionViewHolder>() {
    private val daysOfMonthWithTransaction: List<Int> = monthsTransaction.transactions
        .map { transaction -> transaction.date.dayOfMonth }.distinct().sortedDescending().toList()

    init {
        val months = monthsTransaction.transactions.map { transaction -> transaction.date.month }.distinct()
        val years = monthsTransaction.transactions.map { transaction -> transaction.date.year }.distinct()

        if (months.size > 1) {
            throw IllegalArgumentException("Data for multiple months passed to adaptor - $months")
        } else if (months.size == 1 && months.first() != month) {
            throw IllegalArgumentException("Data is given for month - ${months.first()}, expected month - $month")
        }

        if (years.size > 1) {
            throw IllegalArgumentException("Data for multiple years passed to adaptor - $years")
        } else if (years.size == 1 && years.first() != year) {
            throw IllegalArgumentException("Data is given for year - ${years.first()}, expected year - $year")
        }
    }

    class DayTransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayTransactionViewHolder {
        return DayTransactionViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.day_transaction_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DayTransactionViewHolder, position: Int) {
        val date = LocalDate.of(year, month, daysOfMonthWithTransaction[position])
        val daysTransactions = monthsTransaction.transactions
            .filter { transaction -> transaction.date.dayOfMonth == date.dayOfMonth }
            .toList()

        val income = daysTransactions.filter { transaction -> transaction.amount >= 0.00 }
            .sumOf { transaction -> transaction.amount }

        val expense = daysTransactions.filter { transaction -> transaction.amount < 0.00 }
            .sumOf { transaction -> transaction.amount }

        holder.itemView.apply {
            tvDate.text = date.dayOfMonth.toString()
            tvDay.text = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.UK).toString().uppercase()
            tvMonth.text = date.month.value.toString()
            tvYear.text = date.year.toString()
            tvIncome.text = income.toString()
            tvExpense.text = expense.toString()
            tvTotal.text = (income - expense).toString()
        }

        val transactionAdapter = TransactionAdapter(daysTransactions)
        val linearLayoutManager = LinearLayoutManager(activity)
        holder.itemView.rvTransactionItem.apply {
            adapter = transactionAdapter
            layoutManager = linearLayoutManager
        }
    }

    override fun getItemCount(): Int {
        return daysOfMonthWithTransaction.size
    }
}

















