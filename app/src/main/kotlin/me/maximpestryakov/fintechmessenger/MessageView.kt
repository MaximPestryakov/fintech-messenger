package me.maximpestryakov.fintechmessenger

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.view_message.view.*

class MessageView : RelativeLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    var text: String = ""
        get() = messageText.toString()
        set(value) {
            field = value
            messageText.text = value
        }

    var date: String = ""
        get() = messageDate.toString()
        set(value) {
            field = value
            messageDate.text = value
        }

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.view_message, this)
    }
}
