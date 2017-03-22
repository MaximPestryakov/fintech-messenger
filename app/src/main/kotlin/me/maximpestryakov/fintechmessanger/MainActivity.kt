package me.maximpestryakov.fintechmessanger

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var email: String = ""
    var adapter: DialogListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        email = intent.getStringExtra(EMAIL_KEY)

        initDialogList()

        updateDialogList(listOf("One", "Two", "Three"))
    }

    fun initDialogList() {
        adapter = DialogListAdapter { position ->
            Toast.makeText(this, position.toString(), Toast.LENGTH_SHORT).show()
        }

        dialogList.setHasFixedSize(true)
        dialogList.layoutManager = LinearLayoutManager(this)
        dialogList.adapter = adapter
    }

    fun updateDialogList(dialogs: List<String>) {
        adapter?.dialogs = dialogs
    }
}
