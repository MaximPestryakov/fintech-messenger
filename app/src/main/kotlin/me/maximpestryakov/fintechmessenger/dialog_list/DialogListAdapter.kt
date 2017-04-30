package me.maximpestryakov.fintechmessenger.dialog_list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raizlabs.android.dbflow.kotlinextensions.from
import com.raizlabs.android.dbflow.kotlinextensions.list
import com.raizlabs.android.dbflow.kotlinextensions.select
import kotlinx.android.synthetic.main.item_dialog.view.*
import me.maximpestryakov.fintechmessenger.R
import me.maximpestryakov.fintechmessenger.model.Dialog
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.sdk19.listeners.onClick
import org.jetbrains.anko.uiThread

class DialogListAdapter(val itemClick: (Dialog) -> Unit) :
        RecyclerView.Adapter<DialogListAdapter.ViewHolder>() {

    var dialogs = emptyList<Dialog>()
        set(dialogs) {
            field = dialogs
            notifyDataSetChanged()
        }

    fun update() {
        doAsync {
            val updatedDialogs = (select from Dialog::class).list
            uiThread {
                dialogs = updatedDialogs
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dialog, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = with(holder) {
        bindDialog(dialogs[position])
    }

    override fun getItemCount() = dialogs.size

    class ViewHolder(val view: View, val itemClick: (Dialog) -> Unit) : RecyclerView.ViewHolder(view) {

        fun bindDialog(dialog: Dialog) = with(itemView) {
            itemTitle.text = dialog.title
            itemLastMessage.text = "Last message"
            itemTime.text = "16:37"
            onClick { itemClick(dialog) }
        }
    }
}
