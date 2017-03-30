package me.maximpestryakov.fintechmessenger.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import me.maximpestryakov.fintechmessenger.EMAIL_KEY
import me.maximpestryakov.fintechmessenger.R
import me.maximpestryakov.fintechmessenger.navigation.NavigationActivity

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
