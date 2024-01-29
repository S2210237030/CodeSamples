package at.fhooe.sail.android.beerpongtable

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import at.fhooe.sail.android.beerpongtable.databinding.ActivityWinnerFriendsBinding

lateinit var bindingWinnerFriends: ActivityWinnerFriendsBinding

class WinnerFriendsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingWinnerFriends = ActivityWinnerFriendsBinding.inflate(layoutInflater)
        setContentView(bindingWinnerFriends.root)

        val sP: SharedPreferences = getSharedPreferences(ICC_SHARED_PREF_NAME_FRIENDS, MODE_PRIVATE)
        bindingWinnerFriends.activityWinnerFriendsTvTeam1.text =
            sP.getString(ICC_SHARED_PREF_WINNER, "error")

        bindingWinnerFriends.activityWinnerFriendsBtnReturn.setOnClickListener {
            Intent(this, HomeActivity::class.java).apply { startActivity(this) }
            resetFriendsCounter()
        }
    }

    private fun resetFriendsCounter() {
        cupCounter1_friends = 0
        cupCounter2_friends = 0
    }
}