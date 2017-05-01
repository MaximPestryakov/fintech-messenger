package me.maximpestryakov.fintechmessenger.dialog_list

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.realm.Realm
import kotlinx.android.synthetic.main.item_dialog.view.*
import me.maximpestryakov.fintechmessenger.R
import me.maximpestryakov.fintechmessenger.dialog_list.DialogListAdapter.DialogListViewHolder
import me.maximpestryakov.fintechmessenger.model.Dialog
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.sdk19.listeners.onClick
import org.jetbrains.anko.uiThread
import java.text.SimpleDateFormat
import java.util.*

class DialogListAdapter(val itemClick: (Dialog) -> Unit) : RecyclerView.Adapter<DialogListViewHolder>() {

    private val dateFormat = SimpleDateFormat("hh:mm", Locale.getDefault())

    var dialogs = emptyList<Dialog>()
        set(dialogs) {
            field = dialogs
            notifyDataSetChanged()
        }

    fun update() = doAsync {
        var updatedDialogs = emptyList<Dialog>()
        Realm.getDefaultInstance().use { realm ->
            updatedDialogs = realm.copyFromRealm(realm.where(Dialog::class.java).findAll())
        }
        uiThread {
            dialogs = updatedDialogs
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dialog, parent, false)
        return DialogListViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: DialogListViewHolder, position: Int) = with(holder) {
        bindDialog(dialogs[position], dateFormat)
    }

    override fun getItemCount() = dialogs.size

    class DialogListViewHolder(val view: View, val itemClick: (Dialog) -> Unit) : ViewHolder(view) {

        fun bindDialog(dialog: Dialog, dateFormat: SimpleDateFormat) = with(itemView) {
            itemTitle.text = dialog.title
            itemLastMessage.text = dialog.lastMessage
            itemTime.text = dateFormat.format(Date(dialog.lastDate))
            onClick { itemClick(dialog) }
        }
    }
}
