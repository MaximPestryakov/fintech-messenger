package me.maximpestryakov.fintechmessenger.dialog

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.realm.Realm
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

            Realm.getDefaultInstance().use {

                it.executeTransaction { realm ->
                    val lastId = realm.where(Message::class.java).max("id")?.toInt() ?: 0
                    val message = Message(id = lastId + 1, body = messageText, userId = userId, dialogId = dialogId)
                    realm.copyToRealm(message)
                }
            }
            // sleep(1000)
            uiThread {
                viewState.updateMessageList()
            }
        }
    }
}
