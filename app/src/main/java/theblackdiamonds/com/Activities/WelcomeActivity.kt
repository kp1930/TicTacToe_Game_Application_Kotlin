package theblackdiamonds.com.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_welcome.*
import theblackdiamonds.com.R

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        button.setOnClickListener {
            startActivity(Intent(this@WelcomeActivity, Main2Activity::class.java))
        }
        button2.setOnClickListener {
            startActivity(Intent(this@WelcomeActivity, MainActivity::class.java))
        }
    }
}