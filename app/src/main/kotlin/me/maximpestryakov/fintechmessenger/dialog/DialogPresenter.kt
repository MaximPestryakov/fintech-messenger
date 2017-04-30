package me.maximpestryakov.fintechmessenger.dialog

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.realm.Realm
import me.maximpestryakov.fintechmessenger.model.Dialog
import me.maximpestryakov.fintechmessenger.model.Message
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.lang.Thread.sleep

@InjectViewState
class DialogPresenter : MvpPresenter<DialogView>() {

    override fun attachView(view: DialogView?) {
        super.attachView(view)

        viewState.initMessageList()
        viewState.updateMessageList()
    }

    fun onSendMessage(messageText: String, userId: Int, dialogId: Int) = doAsync {
        sleep(0) // <- Test here

        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                val lastId = realm.where(Message::class.java).max("id")?.toInt() ?: 0
                val message = Message(id = lastId + 1, body = messageText, userId = userId, dialogId = dialogId)
                realm.where(Dialog::class.java).equalTo("id", dialogId).findFirst().lastMessage = messageText
                realm.copyToRealm(message)
            }
        }
        uiThread {
            viewState.updateMessageList()
        }
    }
}

