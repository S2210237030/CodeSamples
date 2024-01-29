package at.fhooe.sail.android.beerpongtable

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import at.fhooe.sail.android.beerpongtable.databinding.ActivityWinnerKoBinding

lateinit var bindingWinnerKo: ActivityWinnerKoBinding

class WinnerKoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingWinnerKo = ActivityWinnerKoBinding.inflate(layoutInflater)
        setContentView(bindingWinnerKo.root)

        val sP: SharedPreferences = getSharedPreferences(ICC_SHARED_PREF_NAME, MODE_PRIVATE)
        bindingWinnerKo.activityWinnerKoTvTeam1.text =
            sP.getString(ICC_SHARED_PREF_WINNER_TOURNAMENT, "error")

        val sP2: SharedPreferences = getSharedPreferences(ICC_SHARED_PREF_NAME, MODE_PRIVATE)
        bindingWinnerKo.activityWinnerKoTvTeam2.text =
            sP2.getString(ICC_SHARED_PREF_SECONDPLACE_TOURNAMENT, "error")

        if (thirdPlaceCounter > thirdPlaceCounter2) {
            val sP3: SharedPreferences = getSharedPreferences(ICC_SHARED_PREF_NAME, MODE_PRIVATE)
            bindingWinnerKo.activityWinnerKoTvTeam3.text =
                sP3.getString(ICC_SHARED_PREF_LOSERGAME1_TOURNAMENT, "error")
        } else if (thirdPlaceCounter < thirdPlaceCounter2) {
            val sP4: SharedPreferences = getSharedPreferences(ICC_SHARED_PREF_NAME, MODE_PRIVATE)
            bindingWinnerKo.activityWinnerKoTvTeam3.text =
                sP4.getString(ICC_SHARED_PREF_LOSERGAME2_TOURNAMENT, "error")
        } else { // add random Team if equal Points
            val rndm = (0..1).random()
            if (rndm == 1) {
                val sP5: SharedPreferences =
                    getSharedPreferences(ICC_SHARED_PREF_NAME, MODE_PRIVATE)
                bindingWinnerKo.activityWinnerKoTvTeam3.text =
                    sP5.getString(ICC_SHARED_PREF_LOSERGAME1_TOURNAMENT, "error")
            } else {
                val sP5: SharedPreferences =
                    getSharedPreferences(ICC_SHARED_PREF_NAME, MODE_PRIVATE)
                bindingWinnerKo.activityWinnerKoTvTeam3.text =
                    sP5.getString(ICC_SHARED_PREF_LOSERGAME2_TOURNAMENT, "error")
            }
            val rndm3rdPlace: String = "The 3rd place was determined by tossing a coin!"
            Toast.makeText(this, rndm3rdPlace, Toast.LENGTH_SHORT).show()
        }




        bindingWinnerKo.activityWinnerKoBtnReturn.setOnClickListener {
            thirdPlaceCounter = 0
            thirdPlaceCounter2 = 0
            Intent(this, HomeActivity::class.java).apply { startActivity(this) }
        }
    }
}