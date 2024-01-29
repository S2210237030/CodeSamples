package at.fhooe.sail.android.beerpongtable

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import at.fhooe.sail.android.beerpongtable.databinding.ActivityTeamKoBinding

class TeamKoActivity : AppCompatActivity() {

    lateinit var bindingTeamKo: ActivityTeamKoBinding
    var flag1: Boolean = false
    var flag2: Boolean = false
    var flag3: Boolean = false
    var flag4: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingTeamKo = ActivityTeamKoBinding.inflate(layoutInflater)
        setContentView(bindingTeamKo.root)

        // button back
        bindingTeamKo.activityTeamKoButtonBack.setOnClickListener {
            Intent(this, RuleKoGamesActivity::class.java).apply { startActivity(this) }
        }

        bindingTeamKo.activityTeamKoButtonStartGame.isEnabled = false

        bindingTeamKo.activityTeamKoInputtextTeam1.addTextChangedListener {
            val text1 = bindingTeamKo.activityTeamKoInputtextTeam1.text.toString()
            val text2 = bindingTeamKo.activityTeamKoInputtextTeam2.text.toString()
            val text3 = bindingTeamKo.activityTeamKoInputtextTeam3.text.toString()
            val text4 = bindingTeamKo.activityTeamKoInputtextTeam4.text.toString()

            if (text4 != "" && text4 != text1 && text4 != text2 && text4 != text3) {
                flag4 = true
            }
            if (text2 != "" && text2 != text1 && text2 != text3 && text2 != text4) {
                flag2 = true
            }
            if (text3 != "" && text3 != text1 && text3 != text2 && text3 != text4) {
                flag3 = true
            }
            if (text1 != "" && text1 != text2 && text1 != text3 && text1 != text4) {
                flag1 = true
                if (flag1 && flag2 && flag3 && flag4) {
                    bindingTeamKo.activityTeamKoButtonStartGame.isEnabled = true
                }
            } else {
                flag1 = false
                bindingTeamKo.activityTeamKoButtonStartGame.isEnabled = false
            }
        }

        bindingTeamKo.activityTeamKoInputtextTeam2.addTextChangedListener {
            val text1 = bindingTeamKo.activityTeamKoInputtextTeam1.text.toString()
            val text2 = bindingTeamKo.activityTeamKoInputtextTeam2.text.toString()
            val text3 = bindingTeamKo.activityTeamKoInputtextTeam3.text.toString()
            val text4 = bindingTeamKo.activityTeamKoInputtextTeam4.text.toString()

            if (text1 != "" && text1 != text2 && text1 != text3 && text1 != text4) {
                flag1 = true
            }
            if (text4 != "" && text4 != text1 && text4 != text2 && text4 != text3) {
                flag4 = true
            }
            if (text3 != "" && text3 != text1 && text3 != text2 && text3 != text4) {
                flag3 = true
            }
            if (text2 != "" && text2 != text1 && text2 != text3 && text2 != text4) {
                flag2 = true
                if (flag1 && flag2 && flag3 && flag4) {
                    bindingTeamKo.activityTeamKoButtonStartGame.isEnabled = true
                }
            } else {
                flag2 = false
                bindingTeamKo.activityTeamKoButtonStartGame.isEnabled = false
            }
        }

        bindingTeamKo.activityTeamKoInputtextTeam3.addTextChangedListener {
            val text1 = bindingTeamKo.activityTeamKoInputtextTeam1.text.toString()
            val text2 = bindingTeamKo.activityTeamKoInputtextTeam2.text.toString()
            val text3 = bindingTeamKo.activityTeamKoInputtextTeam3.text.toString()
            val text4 = bindingTeamKo.activityTeamKoInputtextTeam4.text.toString()

            if (text1 != "" && text1 != text2 && text1 != text3 && text1 != text4) {
                flag1 = true
            }
            if (text2 != "" && text2 != text1 && text2 != text3 && text2 != text4) {
                flag2 = true
            }
            if (text4 != "" && text4 != text1 && text4 != text2 && text4 != text3) {
                flag4 = true
            }
            if (text3 != "" && text3 != text1 && text3 != text2 && text3 != text4) {
                flag3 = true
                if (flag1 && flag2 && flag3 && flag4) {
                    bindingTeamKo.activityTeamKoButtonStartGame.isEnabled = true
                }
            } else {
                flag3 = false
                bindingTeamKo.activityTeamKoButtonStartGame.isEnabled = false
            }
        }

        bindingTeamKo.activityTeamKoInputtextTeam4.addTextChangedListener {
            val text1 = bindingTeamKo.activityTeamKoInputtextTeam1.text.toString()
            val text2 = bindingTeamKo.activityTeamKoInputtextTeam2.text.toString()
            val text3 = bindingTeamKo.activityTeamKoInputtextTeam3.text.toString()
            val text4 = bindingTeamKo.activityTeamKoInputtextTeam4.text.toString()

            if (text1 != "" && text1 != text2 && text1 != text3 && text1 != text4) {
                flag1 = true
            }
            if (text2 != "" && text2 != text1 && text2 != text3 && text2 != text4) {
                flag2 = true
            }
            if (text3 != "" && text3 != text1 && text3 != text2 && text3 != text4) {
                flag3 = true
            }
            if (text4 != "" && text4 != text1 && text4 != text2 && text4 != text3) {
                flag4 = true
                if (flag1 && flag2 && flag3 && flag4) {
                    bindingTeamKo.activityTeamKoButtonStartGame.isEnabled = true
                }
            } else {
                flag4 = false
                bindingTeamKo.activityTeamKoButtonStartGame.isEnabled = false
            }
        }


        // get team names with sharedPreferences
        bindingTeamKo.activityTeamKoButtonStartGame.setOnClickListener {

            val msg1: String = bindingTeamKo.activityTeamKoInputtextTeam1.text.toString()
            val msg2: String = bindingTeamKo.activityTeamKoInputtextTeam2.text.toString()
            val msg3: String = bindingTeamKo.activityTeamKoInputtextTeam3.text.toString()
            val msg4: String = bindingTeamKo.activityTeamKoInputtextTeam4.text.toString()
            val sP: SharedPreferences = getSharedPreferences(ICC_SHARED_PREF_NAME, MODE_PRIVATE)
            val edt: SharedPreferences.Editor = sP.edit()
            edt.putString(ICC_SHARED_PREF_TEAM_1, msg1)
            edt.putString(ICC_SHARED_PREF_TEAM_2, msg2)
            edt.putString(ICC_SHARED_PREF_TEAM_3, msg3)
            edt.putString(ICC_SHARED_PREF_TEAM_4, msg4)
            edt.commit()
            Intent(this, TournamentView::class.java).apply { startActivity(this) }

        }

    }

}
