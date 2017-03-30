package me.maximpestryakov.fintechmessenger

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import me.maximpestryakov.fintechmessenger.model.Dialog
import me.maximpestryakov.fintechmessenger.model.Message

class DialogsFragment private constructor() : Fragment() {

    var userId: Int = 0
    var dialogAdapter: DialogListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userId = arguments?.getInt(USER_ID_KEY) ?: 0
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View = with(inflater) {
        return inflate(R.layout.activity_main, container, false)
    }

    override fun onResume() {
        super.onResume()

        initDialogList()
        updateDialogList(listOf(Dialog(3, Message(4, 3, "Привет", 1490271711))))
    }


    fun initDialogList() {
        dialogAdapter = DialogListAdapter { (id) ->
            Intent(activity, DialogActivity::class.java).apply {
                putExtra(DIALOG_ID_KEY, id)
                startActivity(this)
            }
        }

        dialogList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = dialogAdapter
        }
    }

    fun updateDialogList(dialogs: List<Dialog>) {
        dialogAdapter?.dialogs = dialogs
    }

    companion object {
        val USER_ID_KEY = "user_id"

        fun newInstance(userId: Int) = DialogsFragment().apply {
            arguments = Bundle().apply {
                putInt(USER_ID_KEY, userId)
            }
        }
    }
}
