package com.nishit.learnk

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AddTransactionActivity : AppCompatActivity() {
    private lateinit var tvDate: TextView
    private lateinit var tvTime: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_transaction_layout)

        tvDate = findViewById(R.id.tvDateInput)
        tvTime = findViewById(R.id.tvTimeInput)


    }
}