package me.maximpestryakov.fintechmessenger.dialog_list

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_dialog_list.*
import me.maximpestryakov.fintechmessenger.R
import me.maximpestryakov.fintechmessenger.dialog.DialogActivity
import me.maximpestryakov.fintechmessenger.dialog.DialogActivity.Companion.EXTRA_DIALOG_ID
import me.maximpestryakov.fintechmessenger.model.Dialog
import me.maximpestryakov.fintechmessenger.model.Message
import org.jetbrains.anko.support.v4.startActivity

class DialogListFragment : Fragment() {

    private var userId: Int = 0
    private lateinit var dialogAdapter: DialogListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userId = arguments?.getInt(ARGUMENT_USER_ID) ?: 0
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
            startActivity<DialogActivity>(EXTRA_DIALOG_ID to id)
        }

        dialogList.apply {
            val linearLayoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = dialogAdapter
            addItemDecoration(DividerItemDecoration(context, linearLayoutManager.orientation))
        }
    }

    fun updateDialogList(dialogs: List<Dialog>) {
        dialogAdapter.dialogs = dialogs
    }

    companion object {
        val ARGUMENT_USER_ID = "ARGUMENT_USER_ID"

        fun newInstance(userId: Int) = DialogListFragment().apply {
            arguments = Bundle().apply {
                putInt(ARGUMENT_USER_ID, userId)
            }
        }
    }
}
