package com.vicky7230.flux.ui.tvDetails.info

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vicky7230.flux.R
import com.vicky7230.flux.data.network.model.tvDetails.Cast
import com.vicky7230.flux.utils.GlideApp
import kotlinx.android.synthetic.main.cast_list_item.view.*

/**
 * Created by vicky on 22/3/18.
 */
class CastListAdapter(private val cast: MutableList<Cast>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cast_list_item, parent, false))
    }

    fun addItems(cast: MutableList<Cast>?) {
        this.cast.addAll(cast!!)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return cast.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).onBind(position)
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {
            GlideApp
                    .with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w185" + cast[position].profilePath)
                    .centerInside()
                    .into(itemView.cast_image)

            itemView.cast_name.text = cast[position].name
            itemView.cast_character.text = cast[position].character
        }
    }
}