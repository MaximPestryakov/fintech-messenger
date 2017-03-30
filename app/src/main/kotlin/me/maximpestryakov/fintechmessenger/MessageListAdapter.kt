package me.maximpestryakov.fintechmessenger

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_message_left.view.*
import kotlinx.android.synthetic.main.item_message_right.view.*
import me.maximpestryakov.fintechmessenger.model.Message

class MessageListAdapter(val userId: Int) : RecyclerView.Adapter<MessageListAdapter.ViewHolder>() {

    private val TYPE_USER = 0
    private val TYPE_FRIEND = 1

    var messages = emptyList<Message>()
        set(messages) {
            field = messages
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int) = if (messages[position].senderId == userId) TYPE_USER else TYPE_FRIEND


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val view = if (viewType == TYPE_USER) {
            layoutInflater.inflate(R.layout.item_message_right, parent, false)
        } else {
            layoutInflater.inflate(R.layout.item_message_left, parent, false)
        }

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = with(holder) {
        val message = messages[position]

        if (getItemViewType(position) == TYPE_USER) {
            bindUserMessage(message)
        } else {
            bindFriendMessage(message)
        }
    }


    override fun getItemCount() = messages.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bindUserMessage(message: Message) = with(itemView) {
            messageRight.text = message.body
        }

        fun bindFriendMessage(message: Message) = with(itemView) {
            messageLeft.text = message.body
        }
    }
}
