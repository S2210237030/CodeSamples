package at.fhooe.sail.android.beerpongtable

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import at.fhooe.sail.android.beerpongtable.databinding.ActivityTournamentViewKoBinding

const val ICC_SHARED_PREF_NAME: String = "TeamSharedPreferences"
const val ICC_SHARED_PREF_TEAM_1: String = "Team1"
const val ICC_SHARED_PREF_TEAM_2: String = "Team2"
const val ICC_SHARED_PREF_TEAM_3: String = "Team3"
const val ICC_SHARED_PREF_TEAM_4: String = "Team4"
const val ICC_SHARED_PREF_WINNER_GAME1: String = "WinnerOfGame1"
const val ICC_SHARED_PREF_WINNER_GAME2: String = "WinnerOfGame2"
const val ICC_SHARED_PREF_WINNER_TOURNAMENT: String = "Winner of Tournament"
const val ICC_SHARED_PREF_SECONDPLACE_TOURNAMENT: String = "Second Place of Tournament"
const val ICC_SHARED_PREF_LOSERGAME1_TOURNAMENT: String = "Loser of first semi-final"
const val ICC_SHARED_PREF_LOSERGAME2_TOURNAMENT: String = "Loser of second semi-final"
public var gameCounter: Int = 0 // value to count stage of tournament

class TournamentView : AppCompatActivity() {

    lateinit var bindingTournamentViewKo: ActivityTournamentViewKoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingTournamentViewKo = ActivityTournamentViewKoBinding.inflate(layoutInflater)
        setContentView(bindingTournamentViewKo.root)

        val sP: SharedPreferences = getSharedPreferences(ICC_SHARED_PREF_NAME, MODE_PRIVATE)


        // set textview to game1 or game2, set names of teams
        if (gameCounter == 0) { // 1st semi-final
            bindingTournamentViewKo.activityTournamentViewKoTvGame.text = "SEMI-FINAL"
            bindingTournamentViewKo.activityTournamentViewKoTvTeam1.text = sP.getString(
                ICC_SHARED_PREF_TEAM_1, "error"
            )
            bindingTournamentViewKo.activityTournamentViewKoTvTeam2.text = sP.getString(
                ICC_SHARED_PREF_TEAM_2, "error"
            )
        } else if (gameCounter == 1) { // 2nd semi-final
            bindingTournamentViewKo.activityTournamentViewKoTvGame.text = "SEMI-FINAL"
            bindingTournamentViewKo.activityTournamentViewKoTvTeam1.text = sP.getString(
                ICC_SHARED_PREF_TEAM_3, "error"
            )
            bindingTournamentViewKo.activityTournamentViewKoTvTeam2.text = sP.getString(
                ICC_SHARED_PREF_TEAM_4, "error"
            )
        } else if (gameCounter == 2) {
            bindingTournamentViewKo.activityTournamentViewKoTvGame.text = "FINAL"
            bindingTournamentViewKo.activityTournamentViewKoTvTeam1.text = sP.getString(
                ICC_SHARED_PREF_WINNER_GAME1, "errorFinal"
            )
            bindingTournamentViewKo.activityTournamentViewKoTvTeam2.text = sP.getString(
                ICC_SHARED_PREF_WINNER_GAME2, "errorFinal"
            )
        }

        bindingTournamentViewKo.activityTournamentViewKoBtnReady.setOnClickListener {
            Intent(this, GameKoActivity::class.java).apply {
                startActivity(this)
            }
        }

    }
}