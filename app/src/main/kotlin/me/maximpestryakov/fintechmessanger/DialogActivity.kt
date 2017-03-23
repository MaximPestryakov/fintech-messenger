package me.maximpestryakov.fintechmessanger

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_dialog.*
import me.maximpestryakov.fintechmessanger.model.Message


class DialogActivity : AppCompatActivity() {

    var adapter: MessageListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

        // val dialogId = intent.getIntExtra(DIALOG_ID_KEY, 0)

        initMessageList()

        val messages = listOf(
                Message(4, 3, "Привет", 1490271711),
                Message(5, 123, "Привет)))", 1490271712)
        ).sortedByDescending { it.time }

        updateMessageList(messages)
    }

    fun initMessageList() {
        adapter = MessageListAdapter(123)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true

        messageList.setHasFixedSize(true)
        messageList.layoutManager = layoutManager
        messageList.adapter = adapter
    }

    fun updateMessageList(messages: List<Message>) {
        adapter?.messages = messages
    }
}
