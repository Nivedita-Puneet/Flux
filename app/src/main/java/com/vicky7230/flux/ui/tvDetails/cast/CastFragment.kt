package com.vicky7230.flux.ui.tvDetails.cast


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vicky7230.flux.R


/**
 * A simple [Fragment] subclass.
 */
class CastFragment : Fragment() {

    companion object {
        fun newInstance() = CastFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cast, container, false)
    }

}// Required empty public constructor
