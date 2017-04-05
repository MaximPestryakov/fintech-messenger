package me.maximpestryakov.fintechmessenger.dialog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_dialog.*
import me.maximpestryakov.fintechmessenger.R
import me.maximpestryakov.fintechmessenger.model.Message


class DialogActivity : AppCompatActivity() {

    var messageAdapter = DialogAdapter(123)
    val messages = mutableListOf(Message(4, 3, "Привет", 1490271711), Message(5, 123, "Привет)))", 1490271712))
    var testId = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

        // val dialogId = intent.getIntExtra(DIALOG_ID_KEY, 0)

        initMessageList()

        updateMessageList()
        messageSender.onSendListener = { s: String ->
            messages.add(Message(testId++, 123, s, messages.last().time + testId))
            updateMessageList()
        }
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

    fun updateMessageList() {
        messageAdapter.messages = messages.sortedByDescending { it.time }
    }
}
