package at.fhooe.sail.android.beerpongtable

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import at.fhooe.sail.android.beerpongtable.databinding.ActivityTeamFriendsBinding


class TeamFriendsActivity : AppCompatActivity() {

    lateinit var bindingTeamFriends: ActivityTeamFriendsBinding
    var flag1: Boolean = false
    var flag2: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingTeamFriends = ActivityTeamFriendsBinding.inflate(layoutInflater)
        setContentView(bindingTeamFriends.root)

        // button back
        bindingTeamFriends.activityTeamFriendsButtonBack.setOnClickListener {
            Intent(this, RuleGameFriendsActivity::class.java).apply { startActivity(this) }
        }

        bindingTeamFriends.activityTeamFriendsButtonStartGame.isEnabled = false



        bindingTeamFriends.activityTeamFriendsInputtextTeam1.addTextChangedListener {
            val text1 = bindingTeamFriends.activityTeamFriendsInputtextTeam1.text.toString()
            val text2 = bindingTeamFriends.activityTeamFriendsInputtextTeam2.text.toString()

            if (text2 != "" && text1 != text2) {
                flag2 = true
            }
            if (text1 != "" && text1 != text2) {
                flag1 = true
                if (flag1 && flag2) {
                    bindingTeamFriends.activityTeamFriendsButtonStartGame.isEnabled = true
                }

            } else {
                flag1 = false
                bindingTeamFriends.activityTeamFriendsButtonStartGame.isEnabled = false
            }
        }

        bindingTeamFriends.activityTeamFriendsInputtextTeam2.addTextChangedListener {
            val text1 = bindingTeamFriends.activityTeamFriendsInputtextTeam1.text.toString()
            val text2 = bindingTeamFriends.activityTeamFriendsInputtextTeam2.text.toString()

            if (text1 != "" && text1 != text2) {
                flag1 = true
            }
            if (text2 != "" && text1 != text2) {
                flag2 = true
                if (flag1 && flag2) {
                    bindingTeamFriends.activityTeamFriendsButtonStartGame.isEnabled = true
                }

            } else {
                flag2 = false
                bindingTeamFriends.activityTeamFriendsButtonStartGame.isEnabled = false
            }
        }

        // get team names with sharedPreferences
        bindingTeamFriends.activityTeamFriendsButtonStartGame.setOnClickListener {

            val msg1: String = bindingTeamFriends.activityTeamFriendsInputtextTeam1.text.toString()
            val msg2: String = bindingTeamFriends.activityTeamFriendsInputtextTeam2.text.toString()
            val sP: SharedPreferences = getSharedPreferences(ICC_SHARED_PREF_NAME_FRIENDS, MODE_PRIVATE)
            val edt: SharedPreferences.Editor = sP.edit()
            edt.putString(ICC_SHARED_PREF_TEAM_1_FRIENDS, msg1)
            edt.putString(ICC_SHARED_PREF_TEAM_2_FRIENDS, msg2)
            edt.commit()
            Intent(this, TournamentViewFriends::class.java).apply { startActivity(this) }

        }
    }
}