package me.maximpestryakov.fintechmessenger.dialog

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_dialog.*
import me.maximpestryakov.fintechmessenger.R

class DialogActivity : MvpAppCompatActivity(), DialogView {

    companion object {
        val EXTRA_USER_ID = "me.maximpestryakov.fintechmessenger.dialog.EXTRA_USER_ID"
        val EXTRA_DIALOG_ID = "me.maximpestryakov.fintechmessenger.dialog.EXTRA_DIALOG_ID"
    }

    @InjectPresenter
    lateinit var dialogPresenter: DialogPresenter

    lateinit var dialogAdapter: DialogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

        setSupportActionBar(dialogToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val userId = intent.getIntExtra(EXTRA_DIALOG_ID, 0)
        val dialogId = intent.getIntExtra(EXTRA_DIALOG_ID, 0)

        dialogAdapter = DialogAdapter(userId, dialogId)

        messageSender.onSend { text -> dialogPresenter.onSendMessage(text, userId, dialogId) }
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return true
    }

    override fun initMessageList() {
        messageList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@DialogActivity).apply {
                reverseLayout = true
            }
            adapter = dialogAdapter
        }
    }

    override fun updateMessageList() {
        dialogAdapter.update()
    }
}
