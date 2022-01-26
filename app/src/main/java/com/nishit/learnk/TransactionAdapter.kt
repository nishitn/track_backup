package com.nishit.learnk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nishit.learnk.model.Currency
import com.nishit.learnk.model.Transaction
import kotlinx.android.synthetic.main.transaction_item.view.*

class TransactionAdapter(
    private val transactionItems: List<Transaction>
) : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    private val sortedTransactions: List<Transaction> =
        transactionItems.sortedByDescending { transaction -> transaction.date }

    class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return TransactionViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.transaction_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transactionItem = sortedTransactions[position]
        val currency = Currency.values().firstOrNull { currency -> currency.code == transactionItem.currency }
            ?: throw IllegalArgumentException("Transaction currency ${transactionItem.currency} is not available in currency list")
        holder.itemView.apply {
            tvFrom.text = transactionItem.fromEntityClass
            tvTo.text = transactionItem.toEntityClass
            tvCategory.text = transactionItem.category
            tvNote.text = transactionItem.note
            tvCurrency.text = currency.symbol
            tvAmount.text = transactionItem.amount.toString()
        }
    }

    override fun getItemCount(): Int {
        return transactionItems.size
    }
}