package me.maximpestryakov.fintechmessenger

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.view_message_sender.view.*

class MessageSenderView : RelativeLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    var onSendListener: ((s: String) -> Unit)? = null

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.view_message_sender, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        sendButton.visibility = View.GONE
        sendButton.setOnClickListener {
            onSendListener?.invoke(messageBox.text.toString())
            messageBox.text.clear()
        }

        messageBox.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                sendButton.visibility = if (s.trim().isEmpty()) View.GONE else View.VISIBLE
            }
        })
    }
}
