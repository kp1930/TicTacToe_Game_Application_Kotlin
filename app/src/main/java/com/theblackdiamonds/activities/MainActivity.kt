package com.theblackdiamonds.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.theblackdiamonds.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            startActivity(Intent(this@MainActivity, SinglePlayerActivity::class.java))
        }
        button2.setOnClickListener {
            startActivity(Intent(this@MainActivity, MultiPlayerActivity::class.java))
        }
    }
}