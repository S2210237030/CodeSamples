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
import at.fhooe.sail.android.beerpongtable.databinding.ActivityGameFriendsBinding

var cupCounter1_friends: Int = 0
var cupCounter2_friends: Int = 0

class GameFriendsActivity : AppCompatActivity(), OnClickListener {

    private lateinit var bindingGameFriends: ActivityGameFriendsBinding
    private lateinit var textView: TextView
    private lateinit var textView2: TextView
    private lateinit var changeImageRunnable: Runnable
    private var btnReclick: Array<Boolean> = Array(20) { false }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingGameFriends = ActivityGameFriendsBinding.inflate(layoutInflater)
        setContentView(bindingGameFriends.root)

        setDrawable() // private method for structure
        setOnClickButtons() // private method for structure

        bindingGameFriends.activityGameFriendsBtnExitFinish.setOnClickListener {
            if (cupCounter1_friends == 10 || cupCounter2_friends == 10) {
                if (cupCounter2_friends == 10) {
                    val winner: String =
                        bindingGameFriends.activityGameFriendsTvTeam1.text.toString()
                    val sP: SharedPreferences = getSharedPreferences(ICC_SHARED_PREF_NAME_FRIENDS, MODE_PRIVATE)
                    val edt: SharedPreferences.Editor = sP.edit()
                    edt.putString(ICC_SHARED_PREF_WINNER, winner)
                    edt.commit()
                } else if (cupCounter1_friends == 10) {
                    val winner: String =
                        bindingGameFriends.activityGameFriendsTvTeam2.text.toString()
                    val sP: SharedPreferences = getSharedPreferences(ICC_SHARED_PREF_NAME_FRIENDS, MODE_PRIVATE)
                    val edt: SharedPreferences.Editor = sP.edit()
                    edt.putString(ICC_SHARED_PREF_WINNER, winner)
                    edt.commit()
                }
                Intent(this, WinnerFriendsActivity::class.java).apply { startActivity(this) }
            } else {
                showPopupWindow()
            }
        }

        val sP: SharedPreferences = getSharedPreferences(ICC_SHARED_PREF_NAME_FRIENDS, MODE_PRIVATE)
        bindingGameFriends.activityGameFriendsTvTeam1.text =
            sP.getString(ICC_SHARED_PREF_TEAM_1_FRIENDS, "error")

        val sP2: SharedPreferences =
            getSharedPreferences(ICC_SHARED_PREF_NAME_FRIENDS, MODE_PRIVATE)
        bindingGameFriends.activityGameFriendsTvTeam2.text =
            sP2.getString(ICC_SHARED_PREF_TEAM_2_FRIENDS, "error")

    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun setOnClickButtons() {
        bindingGameFriends.activityGameFriendsCup1Noball.setOnClickListener(this)
        bindingGameFriends.activityGameFriendsCup2Noball.setOnClickListener(this)
        bindingGameFriends.activityGameFriendsCup3Noball.setOnClickListener(this)
        bindingGameFriends.activityGameFriendsCup4Noball.setOnClickListener(this)
        bindingGameFriends.activityGameFriendsCup5Noball.setOnClickListener(this)
        bindingGameFriends.activityGameFriendsCup6Noball.setOnClickListener(this)
        bindingGameFriends.activityGameFriendsCup7Noball.setOnClickListener(this)
        bindingGameFriends.activityGameFriendsCup8Noball.setOnClickListener(this)
        bindingGameFriends.activityGameFriendsCup9Noball.setOnClickListener(this)
        bindingGameFriends.activityGameFriendsCup10Noball.setOnClickListener(this)
        bindingGameFriends.activityGameFriendsCup11Noball.setOnClickListener(this)
        bindingGameFriends.activityGameFriendsCup12Noball.setOnClickListener(this)
        bindingGameFriends.activityGameFriendsCup13Noball.setOnClickListener(this)
        bindingGameFriends.activityGameFriendsCup14Noball.setOnClickListener(this)
        bindingGameFriends.activityGameFriendsCup15Noball.setOnClickListener(this)
        bindingGameFriends.activityGameFriendsCup16Noball.setOnClickListener(this)
        bindingGameFriends.activityGameFriendsCup17Noball.setOnClickListener(this)
        bindingGameFriends.activityGameFriendsCup18Noball.setOnClickListener(this)
        bindingGameFriends.activityGameFriendsCup19Noball.setOnClickListener(this)
        bindingGameFriends.activityGameFriendsCup20Noball.setOnClickListener(this)
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

        bindingGameFriends.activityGameFriendsCup1Noball.setImageDrawable(drawable)
        bindingGameFriends.activityGameFriendsCup2Noball.setImageDrawable(drawable)
        bindingGameFriends.activityGameFriendsCup3Noball.setImageDrawable(drawable)
        bindingGameFriends.activityGameFriendsCup4Noball.setImageDrawable(drawable)
        bindingGameFriends.activityGameFriendsCup5Noball.setImageDrawable(drawable)
        bindingGameFriends.activityGameFriendsCup6Noball.setImageDrawable(drawable)
        bindingGameFriends.activityGameFriendsCup7Noball.setImageDrawable(drawable)
        bindingGameFriends.activityGameFriendsCup8Noball.setImageDrawable(drawable)
        bindingGameFriends.activityGameFriendsCup9Noball.setImageDrawable(drawable)
        bindingGameFriends.activityGameFriendsCup10Noball.setImageDrawable(drawable)
        bindingGameFriends.activityGameFriendsCup11Noball.setImageDrawable(drawable)
        bindingGameFriends.activityGameFriendsCup12Noball.setImageDrawable(drawable)
        bindingGameFriends.activityGameFriendsCup13Noball.setImageDrawable(drawable)
        bindingGameFriends.activityGameFriendsCup14Noball.setImageDrawable(drawable)
        bindingGameFriends.activityGameFriendsCup15Noball.setImageDrawable(drawable)
        bindingGameFriends.activityGameFriendsCup16Noball.setImageDrawable(drawable)
        bindingGameFriends.activityGameFriendsCup17Noball.setImageDrawable(drawable)
        bindingGameFriends.activityGameFriendsCup18Noball.setImageDrawable(drawable)
        bindingGameFriends.activityGameFriendsCup19Noball.setImageDrawable(drawable)
        bindingGameFriends.activityGameFriendsCup20Noball.setImageDrawable(drawable)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onClick(_v: View?) {
        textView = findViewById(R.id.activity_game_friends_tv_punkteanzahl)
        textView2 = findViewById(R.id.activity_game_friends_tv_punkteanzahl2)

        when (_v?.id) {
            R.id.activity_game_friends_cup1_noball -> reassignBtnClick(0, _v)
            R.id.activity_game_friends_cup2_noball -> reassignBtnClick(1, _v)
            R.id.activity_game_friends_cup3_noball -> reassignBtnClick(2, _v)
            R.id.activity_game_friends_cup4_noball -> reassignBtnClick(3, _v)
            R.id.activity_game_friends_cup5_noball -> reassignBtnClick(4, _v)
            R.id.activity_game_friends_cup6_noball -> reassignBtnClick(5, _v)
            R.id.activity_game_friends_cup7_noball -> reassignBtnClick(6, _v)
            R.id.activity_game_friends_cup8_noball -> reassignBtnClick(7, _v)
            R.id.activity_game_friends_cup9_noball -> reassignBtnClick(8, _v)
            R.id.activity_game_friends_cup10_noball -> reassignBtnClick(9, _v)
            R.id.activity_game_friends_cup11_noball -> reassignBtnClick(10, _v)
            R.id.activity_game_friends_cup12_noball -> reassignBtnClick(11, _v)
            R.id.activity_game_friends_cup13_noball -> reassignBtnClick(12, _v)
            R.id.activity_game_friends_cup14_noball -> reassignBtnClick(13, _v)
            R.id.activity_game_friends_cup15_noball -> reassignBtnClick(14, _v)
            R.id.activity_game_friends_cup16_noball -> reassignBtnClick(15, _v)
            R.id.activity_game_friends_cup17_noball -> reassignBtnClick(16, _v)
            R.id.activity_game_friends_cup18_noball -> reassignBtnClick(17, _v)
            R.id.activity_game_friends_cup19_noball -> reassignBtnClick(18, _v)
            R.id.activity_game_friends_cup20_noball -> reassignBtnClick(19, _v)
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun reassignBtnClick(btnIndex: Int, _v: View) {
        if (btnIndex in 0..9) {
            if (!btnReclick[btnIndex]) {
                cupCounter1_friends++
                textView2.text = cupCounter1_friends.toString()
                vibrate()
                changeDrawableBall(_v as ImageButton)
                btnReclick[btnIndex] = true

                // change exit btn to finish if necessary
                if (cupCounter1_friends == 10) {
                    bindingGameFriends.activityGameFriendsBtnExitFinish.text = "FINISH"
                }
            } else {
                cupCounter1_friends--
                textView2.text = cupCounter1_friends.toString()
                rechangeDrawable(_v as ImageButton)
                btnReclick[btnIndex] = false

                // rechange finish btn to exit if necessary
                if (cupCounter1_friends < 10) {
                    bindingGameFriends.activityGameFriendsBtnExitFinish.text = "EXIT"
                }
            }
        } else if (btnIndex in 10..19) {
            if (!btnReclick[btnIndex]) {
                cupCounter2_friends++
                textView.text = cupCounter2_friends.toString()
                vibrate()
                changeDrawableBall(_v as ImageButton)
                btnReclick[btnIndex] = true

                // change exit btn to finish if necessary
                if (cupCounter2_friends == 10) {
                    bindingGameFriends.activityGameFriendsBtnExitFinish.text = "FINISH"
                }
            } else {
                cupCounter2_friends--
                textView.text = cupCounter2_friends.toString()
                rechangeDrawable(_v as ImageButton)
                btnReclick[btnIndex] = false

                // rechange finish btn to exit if necessary
                if (cupCounter2_friends < 10) {
                    bindingGameFriends.activityGameFriendsBtnExitFinish.text = "EXIT"
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun vibrate() {
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
                R.id.activity_game_friends_cup1_noball -> bindingGameFriends.activityGameFriendsCup1Noball.setImageDrawable(
                    drawable2
                )
                R.id.activity_game_friends_cup2_noball -> bindingGameFriends.activityGameFriendsCup2Noball.setImageDrawable(
                    drawable2
                )
                R.id.activity_game_friends_cup3_noball -> bindingGameFriends.activityGameFriendsCup3Noball.setImageDrawable(
                    drawable2
                )
                R.id.activity_game_friends_cup4_noball -> bindingGameFriends.activityGameFriendsCup4Noball.setImageDrawable(
                    drawable2
                )
                R.id.activity_game_friends_cup5_noball -> bindingGameFriends.activityGameFriendsCup5Noball.setImageDrawable(
                    drawable2
                )
                R.id.activity_game_friends_cup6_noball -> bindingGameFriends.activityGameFriendsCup6Noball.setImageDrawable(
                    drawable2
                )
                R.id.activity_game_friends_cup7_noball -> bindingGameFriends.activityGameFriendsCup7Noball.setImageDrawable(
                    drawable2
                )
                R.id.activity_game_friends_cup8_noball -> bindingGameFriends.activityGameFriendsCup8Noball.setImageDrawable(
                    drawable2
                )
                R.id.activity_game_friends_cup9_noball -> bindingGameFriends.activityGameFriendsCup9Noball.setImageDrawable(
                    drawable2
                )
                R.id.activity_game_friends_cup10_noball -> bindingGameFriends.activityGameFriendsCup10Noball.setImageDrawable(
                    drawable2
                )
                R.id.activity_game_friends_cup11_noball -> bindingGameFriends.activityGameFriendsCup11Noball.setImageDrawable(
                    drawable2
                )
                R.id.activity_game_friends_cup12_noball -> bindingGameFriends.activityGameFriendsCup12Noball.setImageDrawable(
                    drawable2
                )
                R.id.activity_game_friends_cup13_noball -> bindingGameFriends.activityGameFriendsCup13Noball.setImageDrawable(
                    drawable2
                )
                R.id.activity_game_friends_cup14_noball -> bindingGameFriends.activityGameFriendsCup14Noball.setImageDrawable(
                    drawable2
                )
                R.id.activity_game_friends_cup15_noball -> bindingGameFriends.activityGameFriendsCup15Noball.setImageDrawable(
                    drawable2
                )
                R.id.activity_game_friends_cup16_noball -> bindingGameFriends.activityGameFriendsCup16Noball.setImageDrawable(
                    drawable2
                )
                R.id.activity_game_friends_cup17_noball -> bindingGameFriends.activityGameFriendsCup17Noball.setImageDrawable(
                    drawable2
                )
                R.id.activity_game_friends_cup18_noball -> bindingGameFriends.activityGameFriendsCup18Noball.setImageDrawable(
                    drawable2
                )
                R.id.activity_game_friends_cup19_noball -> bindingGameFriends.activityGameFriendsCup19Noball.setImageDrawable(
                    drawable2
                )
                R.id.activity_game_friends_cup20_noball -> bindingGameFriends.activityGameFriendsCup20Noball.setImageDrawable(
                    drawable2
                )
            }
        }

        when (view.id) {
            R.id.activity_game_friends_cup1_noball -> {
                bindingGameFriends.activityGameFriendsCup1Noball.setImageDrawable(drawable)
                bindingGameFriends.activityGameFriendsCup1Noball.postDelayed(
                    changeImageRunnable,
                    2000
                )
            }
            R.id.activity_game_friends_cup2_noball -> {
                bindingGameFriends.activityGameFriendsCup2Noball.setImageDrawable(drawable)
                bindingGameFriends.activityGameFriendsCup2Noball.postDelayed(
                    changeImageRunnable,
                    2000
                )
            }
            R.id.activity_game_friends_cup3_noball -> {
                bindingGameFriends.activityGameFriendsCup3Noball.setImageDrawable(drawable)
                bindingGameFriends.activityGameFriendsCup3Noball.postDelayed(
                    changeImageRunnable,
                    2000
                )
            }
            R.id.activity_game_friends_cup4_noball -> {
                bindingGameFriends.activityGameFriendsCup4Noball.setImageDrawable(drawable)
                bindingGameFriends.activityGameFriendsCup4Noball.postDelayed(
                    changeImageRunnable,
                    2000
                )
            }
            R.id.activity_game_friends_cup5_noball -> {
                bindingGameFriends.activityGameFriendsCup5Noball.setImageDrawable(drawable)
                bindingGameFriends.activityGameFriendsCup5Noball.postDelayed(
                    changeImageRunnable,
                    2000
                )
            }
            R.id.activity_game_friends_cup6_noball -> {
                bindingGameFriends.activityGameFriendsCup6Noball.setImageDrawable(drawable)
                bindingGameFriends.activityGameFriendsCup6Noball.postDelayed(
                    changeImageRunnable,
                    2000
                )
            }
            R.id.activity_game_friends_cup7_noball -> {
                bindingGameFriends.activityGameFriendsCup7Noball.setImageDrawable(drawable)
                bindingGameFriends.activityGameFriendsCup7Noball.postDelayed(
                    changeImageRunnable,
                    2000
                )
            }
            R.id.activity_game_friends_cup8_noball -> {
                bindingGameFriends.activityGameFriendsCup8Noball.setImageDrawable(drawable)
                bindingGameFriends.activityGameFriendsCup8Noball.postDelayed(
                    changeImageRunnable,
                    2000
                )
            }
            R.id.activity_game_friends_cup9_noball -> {
                bindingGameFriends.activityGameFriendsCup9Noball.setImageDrawable(drawable)
                bindingGameFriends.activityGameFriendsCup9Noball.postDelayed(
                    changeImageRunnable,
                    2000
                )
            }
            R.id.activity_game_friends_cup10_noball -> {
                bindingGameFriends.activityGameFriendsCup10Noball.setImageDrawable(drawable)
                bindingGameFriends.activityGameFriendsCup10Noball.postDelayed(
                    changeImageRunnable,
                    2000
                )
            }
            R.id.activity_game_friends_cup11_noball -> {
                bindingGameFriends.activityGameFriendsCup11Noball.setImageDrawable(drawable)
                bindingGameFriends.activityGameFriendsCup11Noball.postDelayed(
                    changeImageRunnable,
                    2000
                )
            }
            R.id.activity_game_friends_cup12_noball -> {
                bindingGameFriends.activityGameFriendsCup12Noball.setImageDrawable(drawable)
                bindingGameFriends.activityGameFriendsCup12Noball.postDelayed(
                    changeImageRunnable,
                    2000
                )
            }
            R.id.activity_game_friends_cup13_noball -> {
                bindingGameFriends.activityGameFriendsCup13Noball.setImageDrawable(drawable)
                bindingGameFriends.activityGameFriendsCup13Noball.postDelayed(
                    changeImageRunnable,
                    2000
                )
            }
            R.id.activity_game_friends_cup14_noball -> {
                bindingGameFriends.activityGameFriendsCup14Noball.setImageDrawable(drawable)
                bindingGameFriends.activityGameFriendsCup14Noball.postDelayed(
                    changeImageRunnable,
                    2000
                )
            }
            R.id.activity_game_friends_cup15_noball -> {
                bindingGameFriends.activityGameFriendsCup15Noball.setImageDrawable(drawable)
                bindingGameFriends.activityGameFriendsCup15Noball.postDelayed(
                    changeImageRunnable,
                    2000
                )
            }
            R.id.activity_game_friends_cup16_noball -> {
                bindingGameFriends.activityGameFriendsCup16Noball.setImageDrawable(drawable)
                bindingGameFriends.activityGameFriendsCup15Noball.postDelayed(
                    changeImageRunnable,
                    2000
                )
            }
            R.id.activity_game_friends_cup17_noball -> {
                bindingGameFriends.activityGameFriendsCup17Noball.setImageDrawable(drawable)
                bindingGameFriends.activityGameFriendsCup16Noball.postDelayed(
                    changeImageRunnable,
                    2000
                )
            }
            R.id.activity_game_friends_cup18_noball -> {
                bindingGameFriends.activityGameFriendsCup18Noball.setImageDrawable(drawable)
                bindingGameFriends.activityGameFriendsCup18Noball.postDelayed(
                    changeImageRunnable,
                    2000
                )
            }
            R.id.activity_game_friends_cup19_noball -> {
                bindingGameFriends.activityGameFriendsCup19Noball.setImageDrawable(drawable)
                bindingGameFriends.activityGameFriendsCup19Noball.postDelayed(
                    changeImageRunnable,
                    2000
                )
            }
            R.id.activity_game_friends_cup20_noball -> {
                bindingGameFriends.activityGameFriendsCup20Noball.setImageDrawable(drawable)
                bindingGameFriends.activityGameFriendsCup20Noball.postDelayed(
                    changeImageRunnable,
                    2000
                )
            }
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
        when (view?.id) {
            R.id.activity_game_friends_cup1_noball -> {
                bindingGameFriends.activityGameFriendsCup1Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_friends_cup2_noball -> {
                bindingGameFriends.activityGameFriendsCup2Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_friends_cup3_noball -> {
                bindingGameFriends.activityGameFriendsCup3Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_friends_cup4_noball -> {
                bindingGameFriends.activityGameFriendsCup4Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_friends_cup5_noball -> {
                bindingGameFriends.activityGameFriendsCup5Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_friends_cup6_noball -> {
                bindingGameFriends.activityGameFriendsCup6Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_friends_cup7_noball -> {
                bindingGameFriends.activityGameFriendsCup7Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_friends_cup8_noball -> {
                bindingGameFriends.activityGameFriendsCup8Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_friends_cup9_noball -> {
                bindingGameFriends.activityGameFriendsCup9Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_friends_cup10_noball -> {
                bindingGameFriends.activityGameFriendsCup10Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_friends_cup11_noball -> {
                bindingGameFriends.activityGameFriendsCup11Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_friends_cup12_noball -> {
                bindingGameFriends.activityGameFriendsCup12Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_friends_cup13_noball -> {
                bindingGameFriends.activityGameFriendsCup13Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_friends_cup14_noball -> {
                bindingGameFriends.activityGameFriendsCup14Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_friends_cup15_noball -> {
                bindingGameFriends.activityGameFriendsCup15Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_friends_cup16_noball -> {
                bindingGameFriends.activityGameFriendsCup16Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_friends_cup17_noball -> {
                bindingGameFriends.activityGameFriendsCup17Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_friends_cup18_noball -> {
                bindingGameFriends.activityGameFriendsCup18Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_friends_cup19_noball -> {
                bindingGameFriends.activityGameFriendsCup19Noball.setImageDrawable(
                    drawable
                )
            }
            R.id.activity_game_friends_cup20_noball -> {
                bindingGameFriends.activityGameFriendsCup20Noball.setImageDrawable(
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