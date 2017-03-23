package me.maximpestryakov.fintechmessanger

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_dialog.view.*
import me.maximpestryakov.fintechmessanger.model.Dialog

class DialogListAdapter(val itemClick: (Dialog) -> Unit) :
        RecyclerView.Adapter<DialogListAdapter.ViewHolder>() {

    var dialogs = emptyList<Dialog>()
        set(dialogs) {
            field = dialogs
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dialog, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindDialog(dialogs[position])
    }

    override fun getItemCount(): Int = dialogs.size

    class ViewHolder(val view: View, val itemClick: (Dialog) -> Unit) : RecyclerView.ViewHolder(view) {

        fun bindDialog(dialog: Dialog) {
            view.myTextView.text = dialog.lastMessage.body
            view.setOnClickListener { itemClick(dialog) }
        }
    }
}
