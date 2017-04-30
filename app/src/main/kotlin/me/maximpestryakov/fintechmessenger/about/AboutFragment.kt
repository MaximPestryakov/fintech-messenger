package me.maximpestryakov.fintechmessenger.about

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_about.*
import me.maximpestryakov.fintechmessenger.R
import org.jetbrains.anko.sdk19.listeners.onClick

class AboutFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View = with(inflater) {
        return inflate(R.layout.fragment_about, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        creatorButton.onClick {
            creatorName.visibility = if (creatorName.visibility == GONE) VISIBLE else GONE
            creatorLink.visibility = if (creatorLink.visibility == GONE) VISIBLE else GONE
        }
    }
}
