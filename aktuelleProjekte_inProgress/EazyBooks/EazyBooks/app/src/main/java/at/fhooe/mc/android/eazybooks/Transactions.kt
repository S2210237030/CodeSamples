package at.fhooe.mc.android.eazybooks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Transactions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        setContentView(R.layout.activity_transactions)
    }
}