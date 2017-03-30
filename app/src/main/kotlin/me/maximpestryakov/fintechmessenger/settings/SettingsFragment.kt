package me.maximpestryakov.fintechmessenger.settings

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.maximpestryakov.fintechmessenger.R

class SettingsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View = with(inflater) {
        return inflate(R.layout.fragment_settings, container, false)
    }
}
