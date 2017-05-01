package me.maximpestryakov.fintechmessenger.dialog

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.realm.Realm
import me.maximpestryakov.fintechmessenger.model.Dialog
import me.maximpestryakov.fintechmessenger.model.Message

@InjectViewState
class DialogPresenter : MvpPresenter<DialogView>() {

    private val realm = Realm.getDefaultInstance()

    override fun attachView(view: DialogView?) {
        super.attachView(view)

        viewState.initMessageList()
        viewState.updateMessageList()
    }

    fun onSendMessage(messageText: String, userId: Int, dialogId: Int) {
        realm.executeTransactionAsync({ realm ->
            val lastId = realm.where(Message::class.java).max("id")?.toInt() ?: 0
            val message = Message(id = lastId + 1, body = messageText, userId = userId, dialogId = dialogId)
            realm.copyToRealm(message)
            realm.where(Dialog::class.java).equalTo("id", dialogId).findFirst().lastMessage = messageText
        }, {
            viewState.updateMessageList()
        }, { error ->
            Log.e("Realm", error.message)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}
