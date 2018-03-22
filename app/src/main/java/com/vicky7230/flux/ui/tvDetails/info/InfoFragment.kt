package com.vicky7230.flux.ui.tvDetails.info


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.vicky7230.flux.R
import kotlinx.android.synthetic.main.fragment_info.*


/**
 * A simple [Fragment] subclass.
 */
class InfoFragment : Fragment() {

    companion object {
        fun newInstance() = InfoFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_info, container, false)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        list.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        val items = mutableListOf<ListItem>()
        items.add(ListItem())
        items.add(ListItem())
        items.add(ListItem())
        items.add(ListItem())
        items.add(ListItem())
        items.add(ListItem())
        items.add(ListItem())
        items.add(ListItem())
        items.add(ListItem())
        items.add(ListItem())
        items.add(ListItem())
        items.add(ListItem())
        items.add(ListItem())
        items.add(ListItem())
        items.add(ListItem())
        items.add(ListItem())
        items.add(ListItem())
        items.add(ListItem())
        items.add(ListItem())
        items.add(ListItem())
        items.add(ListItem())
        items.add(ListItem())
        items.add(ListItem())
        items.add(ListItem())
        items.add(ListItem())
        items.add(ListItem())
        items.add(ListItem())

        list.adapter = ListAdapter(items)
    }

}// Required empty public constructor
