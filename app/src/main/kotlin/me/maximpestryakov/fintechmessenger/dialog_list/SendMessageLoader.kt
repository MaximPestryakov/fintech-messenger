package me.maximpestryakov.fintechmessenger.dialog_list

import android.content.Context
import android.support.v4.content.AsyncTaskLoader

class SendMessageLoader(context: Context) : AsyncTaskLoader<String>(context) {

    override fun loadInBackground(): String {
        return ""
    }
}
