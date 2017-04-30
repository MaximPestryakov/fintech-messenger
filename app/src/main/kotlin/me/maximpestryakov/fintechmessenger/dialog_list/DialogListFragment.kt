package me.maximpestryakov.fintechmessenger.dialog_list

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_dialog_list.*
import me.maximpestryakov.fintechmessenger.R
import me.maximpestryakov.fintechmessenger.dialog.DialogActivity
import me.maximpestryakov.fintechmessenger.dialog.DialogActivity.Companion.EXTRA_DIALOG_ID
import me.maximpestryakov.fintechmessenger.dialog.DialogActivity.Companion.EXTRA_USER_ID
import org.jetbrains.anko.sdk19.listeners.onClick
import org.jetbrains.anko.support.v4.startActivity

class DialogListFragment : MvpAppCompatFragment(), DialogListView {

    @InjectPresenter
    lateinit var dialogListPresenter: DialogListPresenter

    private var userId: Int = 0

    private lateinit var dialogAdapter: DialogListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userId = arguments?.getInt(ARGUMENT_USER_ID) ?: 0
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View = with(inflater) {
        inflate(R.layout.fragment_dialog_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addDialog.onClick { dialogListPresenter.onAddDialog() }
    }

    override fun initDialogList() {
        dialogAdapter = DialogListAdapter { dialog ->
            startActivity<DialogActivity>(EXTRA_USER_ID to userId, EXTRA_DIALOG_ID to dialog.id)
        }

        dialogList.apply {
            val linearLayoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = dialogAdapter
            addItemDecoration(DividerItemDecoration(context, linearLayoutManager.orientation))
        }
    }

    override fun updateDialogList() {
        dialogAdapter.update()
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
