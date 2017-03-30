package me.maximpestryakov.fintechmessenger

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_stub.view.*

class StubFragment : Fragment() {

    private var title = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = arguments?.getString(ARG_TITLE) ?: ""
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View = with(inflater) {
        return inflate(R.layout.fragment_stub, container, false).apply {
            textViewStub.text = title
        }
    }

    companion object {

        private val ARG_TITLE = "title"

        fun newInstance(title: String): StubFragment {
            val fragment = StubFragment()
            val args = Bundle()
            args.putString(ARG_TITLE, title)
            fragment.arguments = args
            return fragment
        }
    }
}
