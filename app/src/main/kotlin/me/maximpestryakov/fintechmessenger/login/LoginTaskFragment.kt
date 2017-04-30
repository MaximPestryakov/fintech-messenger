package me.maximpestryakov.fintechmessenger.login

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LoginTaskFragment : Fragment() {

    companion object {

        val TAG = "LoginTaskFragment"

        val newInstance: LoginTaskFragment
            get() = LoginTaskFragment()

        interface LoginListener {
            fun onLogin(userId: Int, email: String)
        }
    }

    private var listener: LoginListener? = null

    private lateinit var email: String

    var started = false
        private set

    var finished = false
        private set

    private var callbackStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    fun start(task: () -> String) {
        if (finished) {
            throw IllegalStateException()
        }
        doAsync {
            started = true
            email = task()
            uiThread {
                if (listener?.onLogin(42, email) != null) {
                    callbackStarted = true
                }
                started = false
                finished = true
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = context as? LoginListener ?: throw IllegalArgumentException()
        if (finished && !callbackStarted) {
            listener?.onLogin(42, email)
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}
