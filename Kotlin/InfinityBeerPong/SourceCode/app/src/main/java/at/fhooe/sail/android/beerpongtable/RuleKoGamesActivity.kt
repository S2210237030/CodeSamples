package at.fhooe.sail.android.beerpongtable

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import at.fhooe.sail.android.beerpongtable.databinding.ActivityRuleKoGamesBinding


class RuleKoGamesActivity : AppCompatActivity() {

    lateinit var bindingRuleKoGames: ActivityRuleKoGamesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingRuleKoGames = ActivityRuleKoGamesBinding.inflate(layoutInflater)
        setContentView(bindingRuleKoGames.root)

        bindingRuleKoGames.activityRuleGameFriendsButtonBacktohome2.setOnClickListener {
            Intent(this, HomeActivity::class.java).apply { startActivity(this) }
        }

        bindingRuleKoGames.activityRuleKoGameCheckBox.setOnClickListener {
          bindingRuleKoGames.activityRuleKoGameButtonSetupteams.isEnabled = bindingRuleKoGames.activityRuleKoGameCheckBox.isChecked
        }

        bindingRuleKoGames.activityRuleKoGameButtonSetupteams.setOnClickListener {
            Intent(this, TeamKoActivity::class.java).apply { startActivity(this) }
        }
    }


}