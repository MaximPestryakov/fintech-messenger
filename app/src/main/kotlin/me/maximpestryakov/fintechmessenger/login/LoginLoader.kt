package me.maximpestryakov.fintechmessenger.login

import android.content.Context
import android.os.Bundle
import android.support.v4.content.AsyncTaskLoader
import java.lang.Thread.sleep

class LoginLoader(context: Context, args: Bundle) : AsyncTaskLoader<String>(context) {

    companion object {
        private val BUNDLE_EMAIL = "BUNDLE_EMAIL"
        private val BUNDLE_PASSWORD = "BUNDLE_PASSWORD"

        fun getInitBundle(email: String, password: String): Bundle = with(Bundle()) {
            putString(BUNDLE_EMAIL, email)
            putString(BUNDLE_PASSWORD, password)
            return this
        }
    }

    private val email: String
    private val password: String

    init {
        email = args.getString(BUNDLE_EMAIL)
        password = args.getString(BUNDLE_PASSWORD)
    }

    override fun loadInBackground(): String {
        sleep(5000)
        return email
    }
}
