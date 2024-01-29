package at.fhooe.sail.android.beerpongtable

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.*
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupWindow
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import at.fhooe.sail.android.beerpongtable.R
import at.fhooe.sail.android.beerpongtable.databinding.ActivityGameKoBinding

var cupCounter1_ko: Int = 0
var cupCounter2_ko: Int = 0
public var thirdPlaceCounter: Int = 0 // counter to detect which looser team had more points
public var thirdPlaceCounter2: Int = 0 // counter to detect which looser team had more points

class GameKoActivity : AppCompatActivity(), OnClickListener {

    lateinit var bindingGameKoActivity: ActivityGameKoBinding
    private lateinit var textView: TextView
    private lateinit var textView2: TextView
    private lateinit var changeImageRunnable: Runnable
    private var btnReclick: Array<Boolean> = Array(20) { false }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingGameKoActivity = ActivityGameKoBinding.inflate(layoutInflater)
        setContentView(bindingGameKoActivity.root)

        setDrawable() // private method for structure
        setOnClickButtons() // private method for structure

        if (gameCounter == 0) { // if first semi-final
            val sP: SharedPreferences = getSharedPreferences(ICC_SHARED_PREF_NAME, MODE_PRIVATE)
            bindingGameKoActivity.activityGameKoTvTeam1.text =
                sP.getString(ICC_SHARED_PREF_TEAM_1, "error")

            val sP2: SharedPreferences = getSharedPreferences(ICC_SHARED_PREF_NAME, MODE_PRIVATE)
            bindingGameKoActivity.activityGameKoTvTeam2.text =
                sP2.getString(ICC_SHARED_PREF_TEAM_2, "error")
        } else if (gameCounter == 1) { // else if 2nd semi-final
            val sP3: SharedPreferences = getSharedPreferences(ICC_SHARED_PREF_NAME, MODE_PRIVATE)
            bindingGameKoActivity.activityGameKoTvTeam1.text =
                sP3.getString(ICC_SHARED_PREF_TEAM_3, "error")

            val sP4: SharedPreferences = getSharedPreferences(ICC_SHARED_PREF_NAME, MODE_PRIVATE)
            bindingGameKoActivity.activityGameKoTvTeam2.text =
                sP4.getString(ICC_SHARED_PREF_TEAM_4, "error")
        } else if (gameCounter == 2) { // final
            val sP5: SharedPreferences = getSharedPreferences(ICC_SHARED_PREF_NAME, MODE_PRIVATE)
            bindingGameKoActivity.activityGameKoTvTeam1.text =
                sP5.getString(ICC_SHARED_PREF_WINNER_GAME1, "errorFinal")

            val sP6: SharedPreferences = getSharedPreferences(ICC_SHARED_PREF_NAME, MODE_PRIVATE)
            bindingGameKoActivity.activityGameKoTvTeam2.text =
                sP6.getString(ICC_SHARED_PREF_WINNER_GAME2, "errorFinal")
        }

        bindingGameKoActivity.activityGameKoBtnExitFinish.setOnClickListener {
            if (cupCounter1_ko == 10 || cupCounter2_ko == 10) { // if game is over
                if (gameCounter == 0) {
                    if (cupCounter2_ko == 10) { // if team1 won
                        val winner1: String =
                            bindingGameKoActivity.activityGameKoTvTeam1.text.toString()
                        val loserGame1: String =
                            bindingGameKoActivity.activityGameKoTvTeam2.text.toString()
                        val sP: SharedPreferences =
                            getSharedPreferences(ICC_SHARED_PREF_NAME, MODE_PRIVATE)
                        val edt: SharedPreferences.Editor = sP.edit()
                        edt.putString(ICC_SHARED_PREF_WINNER_GAME1, winner1)
                        edt.putString(ICC_SHARED_PREF_LOSERGAME1_TOURNAMENT, loserGame1)
                        edt.commit()
                        thirdPlaceCounter = cupCounter1_ko.toInt()
                        gameCounter++
                        cupCounter2_ko = 0
                        cupCounter1_ko = 0// reset points
                        Intent(this, TournamentView::class.java).apply { startActivity(this) }
                    } else if (cupCounter1_ko == 10) { // if team2 won
                        val winner2: String =
                            bindingGameKoActivity.activityGameKoTvTeam2.text.toString()
                        val loserGame1: String =
                            bindingGameKoActivity.activityGameKoTvTeam1.text.toString()
                        val sP: SharedPreferences =
                            getSharedPreferences(ICC_SHARED_PREF_NAME, MODE_PRIVATE)
                        val edt: SharedPreferences.Editor = sP.edit()
                        edt.putString(ICC_SHARED_PREF_WINNER_GAME1, winner2)
                        edt.putString(ICC_SHARED_PREF_LOSERGAME1_TOURNAMENT, loserGame1)
                        edt.commit()
                        thirdPlaceCounter = cupCounter2_ko.toInt()
                        gameCounter++
                        cupCounter1_ko = 0
                        cupCounter2_ko = 0 // reset points
                        Intent(this, TournamentView::class.java).apply { startActivity(this) }
                    }
                }
                if (gameCounter == 1) { // if second semi-final
                    if (cupCounter2_ko == 10) { // if team3 won
                        val winner3: String =
                            bindingGameKoActivity.activityGameKoTvTeam1.text.toString()
                        val loserGame2: String =
                            bindingGameKoActivity.activityGameKoTvTeam2.text.toString()
                        val sP: SharedPreferences =
                            getSharedPreferences(ICC_SHARED_PREF_NAME, MODE_PRIVATE)
                        val edt: SharedPreferences.Editor = sP.edit()
                        edt.putString(ICC_SHARED_PREF_WINNER_GAME2, winner3)
                        edt.putString(ICC_SHARED_PREF_LOSERGAME2_TOURNAMENT, loserGame2)
                        edt.commit()
                        thirdPlaceCounter2 = cupCounter1_ko.toInt()
                        gameCounter++
                        cupCounter1_ko = 0
                        cupCounter2_ko = 0 // reset points
                        Intent(this, TournamentView::class.java).apply { startActivity(this) }
                    } else if (cupCounter1_ko == 10) { // if team4 won
                        val winner4: String =
                            bindingGameKoActivity.activityGameKoTvTeam2.text.toString()
                        val loserGame2: String =
                            bindingGameKoActivity.activityGameKoTvTeam1.text.toString()
                        val sP: SharedPreferences =
                            getSharedPreferences(ICC_SHARED_PREF_NAME, MODE_PRIVATE)
                        val edt: SharedPreferences.Editor = sP.edit()
                        edt.putString(ICC_SHARED_PREF_WINNER_GAME2, winner4)
                        edt.putString(ICC_SHARED_PREF_LOSERGAME2_TOURNAMENT, loserGame2)
                        edt.commit()
                        thirdPlaceCounter2 = cupCounter2_ko.toInt()
                        gameCounter++
                        cupCounter2_ko = 0
                        cupCounter1_ko = 0 // reset points
                        Intent(this, TournamentView::class.java).apply { startActivity(this) }
                    }
                }
                if (gameCounter == 2) { // final
                    if (cupCounter2_ko == 10) {
                        val winnerTournament: String =
                            bindingGameKoActivity.activityGameKoTvTeam1.text.toString()
                        val secondPlace: String =
                            bindingGameKoActivity.activityGameKoTvTeam2.text.toString()
                        val sP: SharedPreferences =
                            getSharedPreferences(ICC_SHARED_PREF_NAME, MODE_PRIVATE)
                        val edt: SharedPreferences.Editor = sP.edit()
                        edt.putString(ICC_SHARED_PREF_WINNER_TOURNAMENT, winnerTournament)
                        edt.putString(ICC_SHARED_PREF_SECONDPLACE_TOURNAMENT, secondPlace)
                        edt.commit()
                        gameCounter = 0
                        cupCounter1_ko = 0
                        cupCounter2_ko = 0 // reset points
                        Intent(this, WinnerKoActivity::class.java).apply { startActivity(this) } // start Winner Activity
                    } else if (cupCounter1_ko == 10) {
                        val winnerTournament: String =
                            bindingGameKoActivity.activityGameKoTvTeam2.text.toString()
                        val secondPlace: String =
                            bindingGameKoActivity.activityGameKoTvTeam1.text.toString()
                        val sP: SharedPreferences =
                            getSharedPreferences(ICC_SHARED_PREF_NAME, MODE_PRIVATE)
                        val edt: SharedPreferences.Editor = sP.edit()
                        edt.putString(ICC_SHARED_PREF_WINNER_TOURNAMENT, winnerTournament)
                        edt.putString(ICC_SHARED_PREF_SECONDPLACE_TOURNAMENT, secondPlace)
                        edt.commit()
                        gameCounter = 0
                        cupCounter1_ko = 0
                        cupCounter2_ko = 0 // reset points
                        Intent(this, WinnerKoActivity::class.java).apply { startActivity(this) } // start Winner Activity
                    }
                }

            // if (cupCounter1_ko == 10 || cupCounter2_ko == 10) {
             //   cupCounter1_ko = 0
             //   cupCounter2_ko = 0
              //  Intent(this, WinnerKoActivity::class.java).apply { startActivity(this) }

            } else {
                showPopupWindow()
            }
        }

    }

    private fun setOnClickButtons() {
        bindingGameKoActivity.activityGameKoCup1Noball.setOnClickListener(this)
        bindingGameKoActivity.activityGameKoCup2Noball.setOnClickListener(this)
        bindingGameKoActivity.activityGameKoCup3Noball.setOnClickListener(this)
        bindingGameKoActivity.activityGameKoCup4Noball.setOnClickListener(this)
        bindingGameKoActivity.activityGameKoCup5Noball.setOnClickListener(this)
        bindingGameKoActivity.activityGameKoCup6Noball.setOnClickListener(this)
        bindingGameKoActivity.activityGameKoCup7Noball.setOnClickListener(this)
        bindingGameKoActivity.activityGameKoCup8Noball.setOnClickListener(this)
        bindingGameKoActivity.activityGameKoCup9Noball.setOnClickListener(this)
        bindingGameKoActivity.activityGameKoCup10Noball.setOnClickListener(this)
        bindingGameKoActivity.activityGameKoCup11Noball.setOnClickListener(this)
        bindingGameKoActivity.activityGameKoCup12Noball.setOnClickListener(this)
        bindingGameKoActivity.activityGameKoCup13Noball.setOnClickListener(this)
        bindingGameKoActivity.activityGameKoCup14Noball.setOnClickListener(this)
        bindingGameKoActivity.activityGameKoCup15Noball.setOnClickListener(this)
        bindingGameKoActivity.activityGameKoCup16Noball.setOnClickListener(this)
        bindingGameKoActivity.activityGameKoCup17Noball.setOnClickListener(this)
        bindingGameKoActivity.activityGameKoCup18Noball.setOnClickListener(this)
        bindingGameKoActivity.activityGameKoCup19Noball.setOnClickListener(this)
        bindingGameKoActivity.activityGameKoCup20Noball.setOnClickListener(this)
    }

    private fun setDrawable() {
        val cupsNoBall = BitmapFactory.decodeResource(resources, R.drawable.nopersp_noball_cup_8x)
        val imgS = Bitmap.createScaledBitmap(
            cupsNoBall, // src image
            65.shl(1), // tgt width
            65.shl(1), // tgt height
            true
        ) // use bilinear filter or not to improve quality

        // Convert the Bitmap to a Drawable
        val drawable: Drawable = BitmapDrawable(resources, imgS)

        bindingGameKoActivity.activityGameKoCup1Noball.setImageDrawable(drawable)
        bindingGameKoActivity.activityGameKoCup2Noball.setImageDrawable(drawable)
        bindingGameKoActivity.activityGameKoCup3Noball.setImageDrawable(drawable)
        bindingGameKoActivity.activityGameKoCup4Noball.setImageDrawable(drawable)
        bindingGameKoActivity.activityGameKoCup5Noball.setImageDrawable(drawable)
        bindingGameKoActivity.activityGameKoCup6Noball.setImageDrawable(drawable)
        bindingGameKoActivity.activityGameKoCup7Noball.setImageDrawable(drawable)
        bindingGameKoActivity.activityGameKoCup8Noball.setImageDrawable(drawable)
        bindingGameKoActivity.activityGameKoCup9Noball.setImageDrawable(drawable)
        bindingGameKoActivity.activityGameKoCup10Noball.setImageDrawable(drawable)
        bindingGameKoActivity.activityGameKoCup11Noball.setImageDrawable(drawable)
        bindingGameKoActivity.activityGameKoCup12Noball.setImageDrawable(drawable)
        bindingGameKoActivity.activityGameKoCup13Noball.setImageDrawable(drawable)
        bindingGameKoActivity.activityGameKoCup14Noball.setImageDrawable(drawable)
        bindingGameKoActivity.activityGameKoCup15Noball.setImageDrawable(drawable)
        bindingGameKoActivity.activityGameKoCup16Noball.setImageDrawable(drawable)
        bindingGameKoActivity.activityGameKoCup17Noball.setImageDrawable(drawable)
        bindingGameKoActivity.activityGameKoCup18Noball.setImageDrawable(drawable)
        bindingGameKoActivity.activityGameKoCup19Noball.setImageDrawable(drawable)
        bindingGameKoActivity.activityGameKoCup20Noball.setImageDrawable(drawable)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onClick(_v: View?) {
        textView = findViewById(R.id.activity_game_ko_tv_punkteanzahl)
        textView2 = findViewById(R.id.activity_game_ko_tv_punkteanzahl2)

        when (_v?.id) {
            R.id.activity_game_ko_cup1_noball -> reassignBtnClick(0, _v)
            R.id.activity_game_ko_cup2_noball -> reassignBtnClick(1, _v)
            R.id.activity_game_ko_cup3_noball -> reassignBtnClick(2, _v)
            R.id.activity_game_ko_cup4_noball -> reassignBtnClick(3, _v)
            R.id.activity_game_ko_cup5_noball -> reassignBtnClick(4, _v)
            R.id.activity_game_ko_cup6_noball -> reassignBtnClick(5, _v)
            R.id.activity_game_ko_cup7_noball -> reassignBtnClick(6, _v)
            R.id.activity_game_ko_cup8_noball -> reassignBtnClick(7, _v)
            R.id.activity_game_ko_cup9_noball -> reassignBtnClick(8, _v)
            R.id.activity_game_ko_cup10_noball -> reassignBtnClick(9, _v)
            R.id.activity_game_ko_cup11_noball -> reassignBtnClick(10, _v)
            R.id.activity_game_ko_cup12_noball -> reassignBtnClick(11, _v)
            R.id.activity_game_ko_cup13_noball -> reassignBtnClick(12, _v)
            R.id.activity_game_ko_cup14_noball -> reassignBtnClick(13, _v)
            R.id.activity_game_ko_cup15_noball -> reassignBtnClick(14, _v)
            R.id.activity_game_ko_cup16_noball -> reassignBtnClick(15, _v)
            R.id.activity_game_ko_cup17_noball -> reassignBtnClick(16, _v)
            R.id.activity_game_ko_cup18_noball -> reassignBtnClick(17, _v)
            R.id.activity_game_ko_cup19_noball -> reassignBtnClick(18, _v)
            R.id.activity_game_ko_cup20_noball -> reassignBtnClick(19, _v)
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun reassignBtnClick(btnIndex: Int, _v: View) {
        if (btnIndex in 0..9) {
            if (!btnReclick[btnIndex]) {
                cupCounter1_ko++
                textView2.text = cupCounter1_ko.toString()
                vibrate()
                changeDrawableBall(_v as ImageButton)
                btnReclick[btnIndex] = true

                // change exit btn to finish if necessary
                if (cupCounter1_ko == 10) {
                    bindingGameKoActivity.activityGameKoBtnExitFinish.text = "FINISH"
                }
            } else {
                cupCounter1_ko--
                textView2.text = cupCounter1_ko.toString()
                rechangeDrawable(_v as ImageButton)
                btnReclick[btnIndex] = false

                // rechange finish btn to exit if necessary
                if (cupCounter1_ko < 10) {
                    bindingGameKoActivity.activityGameKoBtnExitFinish.text = "EXIT"
                }
            }
        } else if (btnIndex in 10..19){
            if (!btnReclick[btnIndex]) {
                cupCounter2_ko++
                textView.text = cupCounter2_ko.toString()
                vibrate()
                changeDrawableBall(_v as ImageButton)
                btnReclick[btnIndex] = true

                // change exit btn to finish if necessary
                if (cupCounter2_ko == 10) {
                    bindingGameKoActivity.activityGameKoBtnExitFinish.text = "FINISH"

                }
            } else {
                cupCounter2_ko--
                textView.text = cupCounter2_ko.toString()
                rechangeDrawable(_v as ImageButton)
                btnReclick[btnIndex] = false

                // rechange finish btn to exit if necessary
                if (cupCounter2_ko < 10) {
                    bindingGameKoActivity.activityGameKoBtnExitFinish.text = "EXIT"
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun vibrate() {
        val vMgr: VibratorManager = getSystemService(VibratorManager::class.java)
        val v: Vibrator = vMgr.defaultVibrator
        val offOnSequence: LongArray =
            longArrayOf(0, 500, 250, 500) //pause, brumm, pause, brumm, in ms
        val vEff: VibrationEffect = VibrationEffect.createWaveform(offOnSequence, -1)
        v.vibrate(vEff)
    }

    private fun changeDrawableBall(view: View) { //button: ImageButton)
        Log.d("ChangeDrawableBall", "Button clicked: ${view.id}")

        val cupsBall = BitmapFactory.decodeResource(resources, R.drawable.nopersp_ball_cup_8x)
        val imgS = Bitmap.createScaledBitmap(
            cupsBall, // src image
            65.shl(1), // tgt width
            65.shl(1), // tgt height
            true
        ) // use bilinear filter or not to improve quality

        // Convert the Bitmap to a Drawable

        val drawable: Drawable = BitmapDrawable(resources, imgS)

        // Create the runnable to change the image
        changeImageRunnable = Runnable {
            // Change the image after a delay
            val emptyCups = BitmapFactory.decodeResource(resources, R.drawable.cup_empty_8x)
            val imgS = Bitmap.createScaledBitmap(
                emptyCups, // src image
                65.shl(1), // tgt width
                65.shl(1), // tgt height
                true
            ) // use bilinear filter or not to improve quality

            // Convert the Bitmap to a Drawable
            val drawable2: Drawable = BitmapDrawable(resources, imgS)

            // Set the new drawable to the ImageButton
            when (view.id) {
                R.id.activity_game_ko_cup1_noball -> bindingGameKoActivity.activityGameKoCup1Noball.setImageDrawable(drawable2)
                R.id.activity_game_ko_cup2_noball -> bindingGameKoActivity.activityGameKoCup2Noball.setImageDrawable(drawable2)
                R.id.activity_game_ko_cup3_noball -> bindingGameKoActivity.activityGameKoCup3Noball.setImageDrawable(drawable2)
                R.id.activity_game_ko_cup4_noball -> bindingGameKoActivity.activityGameKoCup4Noball.setImageDrawable(drawable2)
                R.id.activity_game_ko_cup5_noball -> bindingGameKoActivity.activityGameKoCup5Noball.setImageDrawable(drawable2)
                R.id.activity_game_ko_cup6_noball -> bindingGameKoActivity.activityGameKoCup6Noball.setImageDrawable(drawable2)
                R.id.activity_game_ko_cup7_noball -> bindingGameKoActivity.activityGameKoCup7Noball.setImageDrawable(drawable2)
                R.id.activity_game_ko_cup8_noball -> bindingGameKoActivity.activityGameKoCup8Noball.setImageDrawable(drawable2)
                R.id.activity_game_ko_cup9_noball -> bindingGameKoActivity.activityGameKoCup9Noball.setImageDrawable(drawable2)
                R.id.activity_game_ko_cup10_noball -> bindingGameKoActivity.activityGameKoCup10Noball.setImageDrawable(drawable2)
                R.id.activity_game_ko_cup11_noball -> bindingGameKoActivity.activityGameKoCup11Noball.setImageDrawable(drawable2)
                R.id.activity_game_ko_cup12_noball -> bindingGameKoActivity.activityGameKoCup12Noball.setImageDrawable(drawable2)
                R.id.activity_game_ko_cup13_noball -> bindingGameKoActivity.activityGameKoCup13Noball.setImageDrawable(drawable2)
                R.id.activity_game_ko_cup14_noball -> bindingGameKoActivity.activityGameKoCup14Noball.setImageDrawable(drawable2)
                R.id.activity_game_ko_cup15_noball -> bindingGameKoActivity.activityGameKoCup15Noball.setImageDrawable(drawable2)
                R.id.activity_game_ko_cup16_noball -> bindingGameKoActivity.activityGameKoCup16Noball.setImageDrawable(drawable2)
                R.id.activity_game_ko_cup17_noball -> bindingGameKoActivity.activityGameKoCup17Noball.setImageDrawable(drawable2)
                R.id.activity_game_ko_cup18_noball -> bindingGameKoActivity.activityGameKoCup18Noball.setImageDrawable(drawable2)
                R.id.activity_game_ko_cup19_noball -> bindingGameKoActivity.activityGameKoCup19Noball.setImageDrawable(drawable2)
                R.id.activity_game_ko_cup20_noball -> bindingGameKoActivity.activityGameKoCup20Noball.setImageDrawable(drawable2)
            }
        }

        when (view.id) {
            R.id.activity_game_ko_cup1_noball -> { bindingGameKoActivity.activityGameKoCup1Noball.setImageDrawable(drawable)
                bindingGameKoActivity.activityGameKoCup1Noball.postDelayed(changeImageRunnable, 2000) }
            R.id.activity_game_ko_cup2_noball -> { bindingGameKoActivity.activityGameKoCup2Noball.setImageDrawable(drawable)
                bindingGameKoActivity.activityGameKoCup2Noball.postDelayed(changeImageRunnable, 2000)}
            R.id.activity_game_ko_cup3_noball -> { bindingGameKoActivity.activityGameKoCup3Noball.setImageDrawable(drawable)
                bindingGameKoActivity.activityGameKoCup3Noball.postDelayed(changeImageRunnable, 2000)}
            R.id.activity_game_ko_cup4_noball -> { bindingGameKoActivity.activityGameKoCup4Noball.setImageDrawable(drawable)
                bindingGameKoActivity.activityGameKoCup4Noball.postDelayed(changeImageRunnable, 2000)}
            R.id.activity_game_ko_cup5_noball -> { bindingGameKoActivity.activityGameKoCup5Noball.setImageDrawable(drawable)
                bindingGameKoActivity.activityGameKoCup5Noball.postDelayed(changeImageRunnable, 2000)}
            R.id.activity_game_ko_cup6_noball -> { bindingGameKoActivity.activityGameKoCup6Noball.setImageDrawable(drawable)
                bindingGameKoActivity.activityGameKoCup6Noball.postDelayed(changeImageRunnable, 2000)}
            R.id.activity_game_ko_cup7_noball -> { bindingGameKoActivity.activityGameKoCup7Noball.setImageDrawable(drawable)
                bindingGameKoActivity.activityGameKoCup7Noball.postDelayed(changeImageRunnable, 2000)}
            R.id.activity_game_ko_cup8_noball -> { bindingGameKoActivity.activityGameKoCup8Noball.setImageDrawable(drawable)
                bindingGameKoActivity.activityGameKoCup8Noball.postDelayed(changeImageRunnable, 2000)}
            R.id.activity_game_ko_cup9_noball -> { bindingGameKoActivity.activityGameKoCup9Noball.setImageDrawable(drawable)
                bindingGameKoActivity.activityGameKoCup9Noball.postDelayed(changeImageRunnable, 2000)}
            R.id.activity_game_ko_cup10_noball -> { bindingGameKoActivity.activityGameKoCup10Noball.setImageDrawable(drawable)
                bindingGameKoActivity.activityGameKoCup10Noball.postDelayed(changeImageRunnable, 2000)}
            R.id.activity_game_ko_cup11_noball -> { bindingGameKoActivity.activityGameKoCup11Noball.setImageDrawable(drawable)
                bindingGameKoActivity.activityGameKoCup11Noball.postDelayed(changeImageRunnable, 2000)}
            R.id.activity_game_ko_cup12_noball -> { bindingGameKoActivity.activityGameKoCup12Noball.setImageDrawable(drawable)
                bindingGameKoActivity.activityGameKoCup12Noball.postDelayed(changeImageRunnable, 2000)}
            R.id.activity_game_ko_cup13_noball -> { bindingGameKoActivity.activityGameKoCup13Noball.setImageDrawable(drawable)
                bindingGameKoActivity.activityGameKoCup13Noball.postDelayed(changeImageRunnable, 2000)}
            R.id.activity_game_ko_cup14_noball -> { bindingGameKoActivity.activityGameKoCup14Noball.setImageDrawable(drawable)
                bindingGameKoActivity.activityGameKoCup14Noball.postDelayed(changeImageRunnable, 2000)}
            R.id.activity_game_ko_cup15_noball -> { bindingGameKoActivity.activityGameKoCup15Noball.setImageDrawable(drawable)
                bindingGameKoActivity.activityGameKoCup15Noball.postDelayed(changeImageRunnable, 2000)}
            R.id.activity_game_ko_cup16_noball -> { bindingGameKoActivity.activityGameKoCup16Noball.setImageDrawable(drawable)
                bindingGameKoActivity.activityGameKoCup16Noball.postDelayed(changeImageRunnable, 2000)}
            R.id.activity_game_ko_cup17_noball -> { bindingGameKoActivity.activityGameKoCup17Noball.setImageDrawable(drawable)
                bindingGameKoActivity.activityGameKoCup17Noball.postDelayed(changeImageRunnable, 2000)}
            R.id.activity_game_ko_cup18_noball -> { bindingGameKoActivity.activityGameKoCup18Noball.setImageDrawable(drawable)
                bindingGameKoActivity.activityGameKoCup18Noball.postDelayed(changeImageRunnable, 2000)}
            R.id.activity_game_ko_cup19_noball -> { bindingGameKoActivity.activityGameKoCup19Noball.setImageDrawable(drawable)
                bindingGameKoActivity.activityGameKoCup19Noball.postDelayed(changeImageRunnable, 2000)}
            R.id.activity_game_ko_cup20_noball -> { bindingGameKoActivity.activityGameKoCup20Noball.setImageDrawable(drawable)
                bindingGameKoActivity.activityGameKoCup20Noball.postDelayed(changeImageRunnable, 2000)}
        }
    }

    private fun rechangeDrawable(view: View) { //button: ImageButton)
        Log.d("ChangeDrawableBallEmpty", "Button clicked: ${view.id}")

        val cupsNoBall = BitmapFactory.decodeResource(resources, R.drawable.nopersp_noball_cup_8x)
        val imgS = Bitmap.createScaledBitmap(
            cupsNoBall, // src image
            65.shl(1), // tgt width
            65.shl(1), // tgt height
            true
        ) // use bilinear filter or not to improve quality

        // Convert the Bitmap to a Drawable
        val drawable: Drawable = BitmapDrawable(resources, imgS)

        // Set the new drawable to the ImageButton
        when (view.id) {
            R.id.activity_game_ko_cup1_noball -> {
                bindingGameKoActivity.activityGameKoCup1Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_ko_cup2_noball -> {
                bindingGameKoActivity.activityGameKoCup2Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_ko_cup3_noball -> {
                bindingGameKoActivity.activityGameKoCup3Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_ko_cup4_noball -> {
                bindingGameKoActivity.activityGameKoCup4Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_ko_cup5_noball -> {
                bindingGameKoActivity.activityGameKoCup5Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_ko_cup6_noball -> {
                bindingGameKoActivity.activityGameKoCup6Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_ko_cup7_noball -> {
                bindingGameKoActivity.activityGameKoCup7Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_ko_cup8_noball -> {
                bindingGameKoActivity.activityGameKoCup8Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_ko_cup9_noball -> {
                bindingGameKoActivity.activityGameKoCup9Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_ko_cup10_noball -> {
                bindingGameKoActivity.activityGameKoCup10Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_ko_cup11_noball -> {
                bindingGameKoActivity.activityGameKoCup11Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_ko_cup12_noball -> {
                bindingGameKoActivity.activityGameKoCup12Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_ko_cup13_noball -> {
                bindingGameKoActivity.activityGameKoCup13Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_ko_cup14_noball -> {
                bindingGameKoActivity.activityGameKoCup14Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_ko_cup15_noball -> {
                bindingGameKoActivity.activityGameKoCup15Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_ko_cup16_noball -> {
                bindingGameKoActivity.activityGameKoCup16Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_ko_cup17_noball -> {
                bindingGameKoActivity.activityGameKoCup17Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_ko_cup18_noball -> {
                bindingGameKoActivity.activityGameKoCup18Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_ko_cup19_noball -> {
                bindingGameKoActivity.activityGameKoCup19Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_ko_cup20_noball -> {
                bindingGameKoActivity.activityGameKoCup20Noball.setImageDrawable(
                    drawable
                )
            }
        }
    }

    private fun showPopupWindow() {
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.popup_layout, null)

        val popupWindow = PopupWindow(popupView, 800, 500, true)

        // Set up button click listener in the popup window
        val yesBtn = popupView.findViewById<Button>(R.id.popup_window_btn_yes)
        yesBtn.setOnClickListener {
            popupWindow.dismiss()
            /*
            if (gameCounter == 1) { // if second semifinal has to be played
                Intent(this, TournamentView::class.java).apply { startActivity(this) }
            } else if (gameCounter == 2) { // if final has to be played
                Intent(this, TournamentView::class.java).apply { startActivity(this) }
            } else { // else if tournament is over -> start final screen

            }
             */
            Intent(this, HomeActivity::class.java).apply { startActivity(this) }

        }

        val noBtn = popupView.findViewById<Button>(R.id.popup_window_btn_no)
        noBtn.setOnClickListener {
            popupWindow.dismiss()
        }

        // Show the popup window at the center of the activity's root view
        val rootView = window.decorView.rootView
        popupWindow.showAtLocation(rootView, Gravity.CENTER, 0, 0)
    }
}