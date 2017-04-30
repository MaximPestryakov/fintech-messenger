package me.maximpestryakov.fintechmessenger

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.view_message_sender.view.*
import org.jetbrains.anko.sdk19.listeners.onClick
import org.jetbrains.anko.sdk19.listeners.textChangedListener

class MessageSenderView : RelativeLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    private var onSendListener: ((s: String) -> Unit) = {}

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.view_message_sender, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        sendButton.visibility = GONE

        sendButton.onClick {
            onSendListener(messageBox.text.toString())
            messageBox.text.clear()
        }

        messageBox.textChangedListener {
            onTextChanged { s, _, _, _ ->
                sendButton.visibility = if (s != null && s.trim().isEmpty()) GONE else VISIBLE
            }
        }
    }

    fun onSend(l: (String) -> Unit) {
        onSendListener = l
    }
}
