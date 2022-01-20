package com.nishit.learnk

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.day_transaction_item.view.*
import java.time.format.TextStyle
import java.util.*

class DayTransactionAdapter(
    private val activity: Activity,
    private val dayTransactionItems: MutableList<DayTransactionItem>,
    private val transactionItems: MutableList<TransactionItem>
) : RecyclerView.Adapter<DayTransactionAdapter.DayTransactionViewHolder>() {

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

    fun addDayTransactionItem(dayTransactionItem: DayTransactionItem) {
        dayTransactionItems.add(dayTransactionItem)
        notifyItemInserted(dayTransactionItems.size - 1)
    }

    override fun onBindViewHolder(holder: DayTransactionViewHolder, position: Int) {
        val dayTransactionItem = dayTransactionItems[position]
        holder.itemView.apply {
            tvDate.text = dayTransactionItem.date.dayOfMonth.toString()
            tvDay.text = dayTransactionItem.date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.UK).toString().uppercase()
            tvMonth.text = dayTransactionItem.date.month.value.toString()
            tvYear.text = dayTransactionItem.date.year.toString()
            tvIncome.text = dayTransactionItem.income.toString()
            tvExpense.text = dayTransactionItem.expense.toString()
            tvIncome.text = (dayTransactionItem.income - dayTransactionItem.expense).toString()
        }

        val transactionAdapter = TransactionAdapter(transactionItems)
        val linearLayoutManager = LinearLayoutManager(activity)
        holder.itemView.rvTransactionItem.apply {
            adapter = transactionAdapter
            layoutManager = linearLayoutManager
        }
    }

    override fun getItemCount(): Int {
        return dayTransactionItems.size
    }
}

















