package me.maximpestryakov.fintechmessenger

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login.setOnClickListener {
            Intent(this, NavigationActivity::class.java).apply {
                putExtra(EMAIL_KEY, email.text.toString());
                startActivity(this)
            }
        }
    }
}