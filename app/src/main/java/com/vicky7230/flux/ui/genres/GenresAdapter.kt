package com.vicky7230.flux.ui.genres

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vicky7230.flux.R
import com.vicky7230.flux.data.network.model.genres.Genre
import kotlinx.android.synthetic.main.genre_list_item.view.*

/**
 * Created by vicky on 28/2/18.
 */
class GenresAdapter(private val genres: MutableList<Genre>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun addItems(genres: MutableList<Genre>?) {
        if (genres != null) {
            this.genres?.addAll(genres)
            notifyItemRangeInserted(0, genres.size)
        }
    }


    override fun getItemCount(): Int {
        return genres?.size ?: 0
    }

    fun getItem(position: Int): Genre? {
        return if (position != RecyclerView.NO_POSITION)
            genres?.get(position)
        else
            null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val genreViewHolder = GenreViewHolder(
            LayoutInflater.from(parent?.context).inflate(
                R.layout.genre_list_item,
                parent,
                false
            )
        )

        genreViewHolder.itemView.setOnClickListener(View.OnClickListener {
            val genre = getItem(genreViewHolder.adapterPosition)
            if (genre != null) {
                genreViewHolder.itemView.check_box.isChecked = !genre.selected
                genre.selected = !genre.selected
            }
        })

        return genreViewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GenreViewHolder).onBind(position)
    }

    inner class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(position: Int) {
            val genre: Genre? = genres?.get(position)
            itemView.genre_name.text = genre?.name
            itemView.check_box.isChecked = genre?.selected!!

            when (genre.id) {
                10759 -> itemView.genre_icon.setImageResource(R.drawable.ic_action_gray_24dp)
                16 -> itemView.genre_icon.setImageResource(R.drawable.ic_animation_gray_24dp)
                35 -> itemView.genre_icon.setImageResource(R.drawable.ic_comedy_gray_24dp)
                80 -> itemView.genre_icon.setImageResource(R.drawable.ic_crime_gray_24dp)
                99 -> itemView.genre_icon.setImageResource(R.drawable.ic_documentry_gray_24dp)
                18 -> itemView.genre_icon.setImageResource(R.drawable.ic_drama_gray_24dp)
                10751 -> itemView.genre_icon.setImageResource(R.drawable.ic_family_gray_24dp)
                10762 -> itemView.genre_icon.setImageResource(R.drawable.ic_kids_gray_24dp)
                9648 -> itemView.genre_icon.setImageResource(R.drawable.ic_mystery_gray_24dp)
                10763 -> itemView.genre_icon.setImageResource(R.drawable.ic_news_gray_24dp)
                10764 -> itemView.genre_icon.setImageResource(R.drawable.ic_reality_gray_24dp)
                10765 -> itemView.genre_icon.setImageResource(R.drawable.ic_sci_fi_gray_24dp)
                10766 -> itemView.genre_icon.setImageResource(R.drawable.ic_soap_gray_24dp)
                10767 -> itemView.genre_icon.setImageResource(R.drawable.ic_talk_gray_24dp)
                10768 -> itemView.genre_icon.setImageResource(R.drawable.ic_war_gray_24dp)
                37 -> itemView.genre_icon.setImageResource(R.drawable.ic_western_gray_24dp)
            }
        }
    }
}