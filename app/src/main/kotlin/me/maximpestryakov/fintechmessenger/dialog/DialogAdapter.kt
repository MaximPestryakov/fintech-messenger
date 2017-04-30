package me.maximpestryakov.fintechmessenger.dialog

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.realm.Realm
import io.realm.Sort.DESCENDING
import kotlinx.android.synthetic.main.item_message_left.view.*
import kotlinx.android.synthetic.main.item_message_right.view.*
import me.maximpestryakov.fintechmessenger.R
import me.maximpestryakov.fintechmessenger.dialog.DialogAdapter.DialogViewHolder
import me.maximpestryakov.fintechmessenger.model.Message
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DialogAdapter(val userId: Int, val currentDialogId: Int) : RecyclerView.Adapter<DialogViewHolder>() {

    private val TYPE_USER = 0
    private val TYPE_FRIEND = 1

    private var messages = emptyList<Message>()
        set(messages) {
            field = messages
            notifyDataSetChanged()
        }

    fun update() = doAsync {
        var updatedMessages = emptyList<Message>()
        Realm.getDefaultInstance().use { realm ->
            updatedMessages = realm.copyFromRealm(realm.where(Message::class.java)
                    .equalTo("dialogId", currentDialogId)
                    .findAllSorted("date", DESCENDING))
        }
        uiThread {
            messages = updatedMessages
        }
    }

    override fun getItemViewType(position: Int) = if (messages[position].userId == userId) TYPE_USER else TYPE_FRIEND

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = inflater.inflate(
                if (viewType == TYPE_USER) R.layout.item_message_right
                else R.layout.item_message_left, parent, false)

        return DialogViewHolder(view)
    }

    override fun onBindViewHolder(holder: DialogViewHolder, position: Int) = with(holder) {
        val message = messages[position]

        if (getItemViewType(position) == TYPE_USER) {
            bindUserMessage(message)
        } else {
            bindFriendMessage(message)
        }
    }

    override fun getItemCount() = messages.size

    class DialogViewHolder(val view: View) : ViewHolder(view) {

        fun bindUserMessage(message: Message) = with(itemView) {
            messageRight.text = message.body
            messageRight.date = "4:20"
        }

        fun bindFriendMessage(message: Message) = with(itemView) {
            messageLeft.text = message.body
            messageLeft.date = "4:19"
        }
    }
}
