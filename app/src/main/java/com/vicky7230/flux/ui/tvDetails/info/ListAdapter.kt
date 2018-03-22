package com.vicky7230.flux.ui.tvDetails.info

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vicky7230.flux.R
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * Created by vicky on 22/3/18.
 */
class ListAdapter(private val items: MutableList<ListItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).onBind(position)
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {
            itemView.name.text = items[position].name
            itemView.age.text = items[position].age.toString()
        }
    }
}