package at.fhooe.sail.android.beerpongtable

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import at.fhooe.sail.android.beerpongtable.databinding.ActivityTournamentViewFriendsBinding


const val ICC_SHARED_PREF_NAME_FRIENDS: String = "TeamSharedPreferences"
const val ICC_SHARED_PREF_TEAM_1_FRIENDS: String = "Team1"
const val ICC_SHARED_PREF_TEAM_2_FRIENDS: String = "Team2"
const val ICC_SHARED_PREF_WINNER: String = "WinnerFriends"


class TournamentViewFriends : AppCompatActivity() {

    lateinit var bindingTournamentViewFriends: ActivityTournamentViewFriendsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingTournamentViewFriends = ActivityTournamentViewFriendsBinding.inflate(layoutInflater)
        setContentView(bindingTournamentViewFriends.root)

        val sP: SharedPreferences = getSharedPreferences(ICC_SHARED_PREF_NAME_FRIENDS, MODE_PRIVATE)

        // get names of team1 and team2
        bindingTournamentViewFriends.activityTournamentViewFriendsTvTeam1.text = sP.getString(
            ICC_SHARED_PREF_TEAM_1_FRIENDS, "error"
        )
        bindingTournamentViewFriends.activityTournamentViewFriendsTvTeam2.text = sP.getString(
            ICC_SHARED_PREF_TEAM_2_FRIENDS, "error"
        )

        bindingTournamentViewFriends.activityTournamentViewFriendsBtnReady.setOnClickListener {
            Intent(this, GameFriendsActivity::class.java).apply {
                startActivity(this)
            }
        }

    }
}