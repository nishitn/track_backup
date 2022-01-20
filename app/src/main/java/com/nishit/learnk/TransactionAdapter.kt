package com.nishit.learnk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.transaction_item.view.*

class TransactionAdapter(
    private val transactionItems: MutableList<TransactionItem>
) : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

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
        val transactionItem = transactionItems[position]
        holder.itemView.apply {
            tvFrom.text = transactionItem.from
            tvTo.text = transactionItem.to
            tvCategory.text = transactionItem.category
            tvNote.text = transactionItem.note
            tvCurrency.text = transactionItem.currency
            tvAmount.text = transactionItem.amount.toString()
        }
    }

    override fun getItemCount(): Int {
        return transactionItems.size
    }
}