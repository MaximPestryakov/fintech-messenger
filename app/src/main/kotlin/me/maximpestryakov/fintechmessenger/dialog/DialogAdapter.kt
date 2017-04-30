package me.maximpestryakov.fintechmessenger.dialog

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raizlabs.android.dbflow.kotlinextensions.*
import com.raizlabs.android.dbflow.sql.language.OrderBy.fromProperty
import kotlinx.android.synthetic.main.item_message_left.view.*
import kotlinx.android.synthetic.main.item_message_right.view.*
import me.maximpestryakov.fintechmessenger.R
import me.maximpestryakov.fintechmessenger.model.Message
import me.maximpestryakov.fintechmessenger.model.Message_Table.date
import me.maximpestryakov.fintechmessenger.model.Message_Table.dialogId
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DialogAdapter(val userId: Int, val currentDialogId: Int) : RecyclerView.Adapter<DialogAdapter.ViewHolder>() {

    private val TYPE_USER = 0
    private val TYPE_FRIEND = 1

    private var messages = emptyList<Message>()
        set(messages) {
            field = messages
            notifyDataSetChanged()
        }

    fun update() {
        doAsync {
            val updatedMessages = (select
                    from Message::class
                    where (dialogId eq currentDialogId)
                    orderBy fromProperty(date).descending()
                    ).list
            uiThread {
                messages = updatedMessages
            }
        }
    }

    override fun getItemViewType(position: Int) = if (messages[position].userId == userId) TYPE_USER else TYPE_FRIEND

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = inflater.inflate(
                if (viewType == TYPE_USER) R.layout.item_message_right
                else R.layout.item_message_left, parent, false)

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
            messageRight.date = "4:20"
        }

        fun bindFriendMessage(message: Message) = with(itemView) {
            messageLeft.text = message.body
            messageLeft.date = "4:19"
        }
    }
}
