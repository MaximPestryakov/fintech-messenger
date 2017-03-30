package me.maximpestryakov.fintechmessenger.dialog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_dialog.*
import me.maximpestryakov.fintechmessenger.R
import me.maximpestryakov.fintechmessenger.model.Message


class DialogActivity : AppCompatActivity() {

    var messageAdapter = DialogAdapter(123)

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
        messageList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@DialogActivity).apply {
                reverseLayout = true
            }
            adapter = messageAdapter
        }
    }

    fun updateMessageList(messages: List<Message>) {
        messageAdapter.messages = messages
    }
}
