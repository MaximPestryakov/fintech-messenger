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
            fun onLogin(email: String)
        }
    }

    private var listener: LoginListener? = null

    private lateinit var result: String

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
            result = task()
            uiThread {
                if (listener?.onLogin(result) != null) {
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
            listener?.onLogin(result)
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}
