package at.fhooe.sail.android.beerpongtable

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import at.fhooe.sail.android.beerpongtable.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var bindingHome: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingHome = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(bindingHome.root)

        bindingHome.activityHomeButtonFriendsgame.setOnClickListener {
            Intent(this, RuleGameFriendsActivity::class.java).apply { startActivity(this) }
        }
        bindingHome.activityHomeButtonKogames.setOnClickListener {
            Intent(this, RuleKoGamesActivity::class.java).apply { startActivity(this) }
        }
    }
}