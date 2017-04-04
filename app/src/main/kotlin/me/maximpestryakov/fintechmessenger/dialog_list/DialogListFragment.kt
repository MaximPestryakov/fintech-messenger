package me.maximpestryakov.fintechmessenger.dialog_list

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_dialog_list.*
import me.maximpestryakov.fintechmessenger.DIALOG_ID_KEY
import me.maximpestryakov.fintechmessenger.R
import me.maximpestryakov.fintechmessenger.dialog.DialogActivity
import me.maximpestryakov.fintechmessenger.model.Dialog
import me.maximpestryakov.fintechmessenger.model.Message

class DialogListFragment : Fragment() {

    var userId: Int = 0
    private lateinit var dialogAdapter: DialogListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userId = arguments?.getInt(USER_ID_KEY) ?: 0
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View = with(inflater) {
        return inflate(R.layout.fragment_dialog_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        fun newInstance(userId: Int) = DialogListFragment().apply {
            arguments = Bundle().apply {
                putInt(USER_ID_KEY, userId)
            }
        }
    }
}
