package at.fhooe.sail.android.beerpongtable

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import at.fhooe.sail.android.beerpongtable.databinding.ActivityRuleGameFriendsBinding

lateinit var bindingRuleFriends: ActivityRuleGameFriendsBinding

class RuleGameFriendsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingRuleFriends = ActivityRuleGameFriendsBinding.inflate(layoutInflater)
        setContentView(bindingRuleFriends.root)

        bindingRuleFriends.activityRuleGameFriendsButtonBacktohome.setOnClickListener {
            Intent(this, HomeActivity::class.java).apply { startActivity(this) }
        }

        bindingRuleFriends.activityRuleGameFriendsCheckBox.setOnClickListener {
            bindingRuleFriends.activityRuleGameFriendsButtonSetupteams.isEnabled = bindingRuleFriends.activityRuleGameFriendsCheckBox.isChecked
        }

        bindingRuleFriends.activityRuleGameFriendsButtonSetupteams.setOnClickListener {
            Intent(this, TeamFriendsActivity::class.java).apply { startActivity(this) }
        }
    }
}