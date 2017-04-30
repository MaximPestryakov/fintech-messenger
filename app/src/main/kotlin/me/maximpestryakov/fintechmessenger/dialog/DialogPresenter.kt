package me.maximpestryakov.fintechmessenger.dialog

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.raizlabs.android.dbflow.kotlinextensions.save
import me.maximpestryakov.fintechmessenger.model.Message
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

@InjectViewState
class DialogPresenter : MvpPresenter<DialogView>() {

    init {
        viewState.initMessageList()
        viewState.updateMessageList()
    }

    fun onSendMessage(messageText: String, userId: Int, dialogId: Int) {
        doAsync {
            val message = Message(body = messageText, userId = userId, dialogId = dialogId)
            message.save()
            // sleep(1000)
            uiThread {
                viewState.updateMessageList()
            }
        }
    }
}
