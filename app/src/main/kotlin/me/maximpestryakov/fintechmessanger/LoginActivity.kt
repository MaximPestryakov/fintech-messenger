package me.maximpestryakov.fintechmessanger

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(EMAIL_KEY, email.text.toString());
            startActivity(intent)
        }
    }
}
