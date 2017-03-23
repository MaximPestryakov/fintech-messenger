package me.maximpestryakov.fintechmessanger

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import me.maximpestryakov.fintechmessanger.model.Dialog
import me.maximpestryakov.fintechmessanger.model.Message


class MainActivity : AppCompatActivity() {

    var email: String = ""
    var adapter: DialogListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        email = intent.getStringExtra(EMAIL_KEY)

        initDialogList()

        updateDialogList(listOf(Dialog(3, Message(4, 3, "Привет", 1490271711))))
    }

    fun initDialogList() {
        adapter = DialogListAdapter { (id) ->
            val intent = Intent(this, DialogActivity::class.java)
            intent.putExtra(DIALOG_ID_KEY, id)
            startActivity(intent)

            // Toast.makeText(this, position.toString(), Toast.LENGTH_SHORT).show()
        }

        dialogList.setHasFixedSize(true)
        dialogList.layoutManager = LinearLayoutManager(this)
        dialogList.adapter = adapter
    }

    fun updateDialogList(dialogs: List<Dialog>) {
        adapter?.dialogs = dialogs
    }
}
