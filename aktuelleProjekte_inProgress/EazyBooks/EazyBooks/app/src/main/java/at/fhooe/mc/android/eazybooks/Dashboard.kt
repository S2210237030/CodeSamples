package at.fhooe.mc.android.eazybooks

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity


class Dashboard : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        setContentView(R.layout.activity_dashboard)

        // Finde die SimplePieChart-View in deinem Layout
        val pieChart = findViewById<SimplePieChart>(R.id.dashboard_pie_chart)

        // Setze die Prozentsätze für Einnahmen und Ausgaben
        val incomePercentage = 60f
        val expensePercentage = 40f

        // Berechne die Werte für das Kreisdiagramm
        val values = floatArrayOf(incomePercentage, expensePercentage)

        // Setze die Werte für das Kreisdiagramm
        pieChart.setValues(values)

        // Finde den Button für Transaktionen in deinem Layout
        val transactionsButton = findViewById<ImageButton>(R.id.dashboard_transactions)

        // Füge einen Klick-Listener zum Button hinzu
        transactionsButton.setOnClickListener {
            // Erstelle ein Intent, um zur TransactionsActivity zu wechseln
            val intent = Intent(this, Transactions::class.java)

            // Starte die TransactionsActivity
            startActivity(intent)
        }

    }
}

class SimplePieChart(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val colors = intArrayOf(Color.rgb(0, 242, 32), Color.rgb(255, 55, 41))
    private var values = floatArrayOf(60f, 40f)

    fun setValues(newValues: FloatArray) {
        require(newValues.size == colors.size) { "Number of values must match the number of colors" }
        values = newValues
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = width.coerceAtMost(height) / 2f

        var startAngle = 0f
        for (i in colors.indices) {
            paint.color = colors[i]
            val sweepAngle = 360 * (values[i] / values.sum())
            canvas.drawArc(centerX - radius, centerY - radius, centerX + radius, centerY + radius, startAngle, sweepAngle, true, paint)
            startAngle += sweepAngle
        }
    }
}