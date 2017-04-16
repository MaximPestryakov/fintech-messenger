package me.maximpestryakov.fintechmessenger.dialog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_dialog.*
import me.maximpestryakov.fintechmessenger.R
import me.maximpestryakov.fintechmessenger.model.Message

class DialogActivity : AppCompatActivity() {

    companion object {
        val EXTRA_DIALOG_ID = "me.maximpestryakov.fintechmessenger.dialog.EXTRA_DIALOG_ID"
    }

    val dialogAdapter = DialogAdapter(123)
    val messages = mutableListOf(Message(4, 3, "Привет", 1490271711), Message(5, 123, "Привет)))", 1490271712))
    var testId = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

        // val dialogId = intent.getIntExtra(EXTRA_DIALOG_ID, 0)

        initMessageList()

        updateMessageList()
        messageSender.onSendListener = { s ->
            messages.add(Message(testId++, 123, s.trim(), messages.last().time + testId))
            updateMessageList()
        }
    }

    fun initMessageList() {
        messageList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@DialogActivity).apply {
                reverseLayout = true
            }
            adapter = dialogAdapter
        }
    }

    fun updateMessageList() {
        dialogAdapter.messages = messages.sortedByDescending { it.time }
    }
}
