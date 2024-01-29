package at.fhooe.mc.android.eazybooks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {

    private val SPLASH_DELAY: Long = 3000 // 3 Sekunden

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        setContentView(R.layout.activity_main)

        // Verzögerung für 3 Sekunden, dann starte die nächste Aktivität
        Handler().postDelayed({
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
            finish() // Beende die Splashscreen-Aktivität, damit der Benutzer nicht zurückkehren kann
        }, SPLASH_DELAY)
    }
}