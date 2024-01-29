package at.fhooe.sail.android.beerpongtable

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import at.fhooe.sail.android.beerpongtable.databinding.ActivityMainBinding

const val TAG: String ="InfinityBeerPong"

class MainActivity : AppCompatActivity(), OnClickListener {

    lateinit var binding: ActivityMainBinding
    lateinit var mHandler: Handler

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mHandler = Handler(mainLooper)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mHandler.postDelayed({
            val i: Intent = Intent(this, HomeActivity::class.java)
            startActivity(i)
            finish()
        }, 2000)
    }

    override fun onClick(p0: View?) {
    }
}