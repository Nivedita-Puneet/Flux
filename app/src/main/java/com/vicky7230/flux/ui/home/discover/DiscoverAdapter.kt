package com.vicky7230.flux.ui.home.discover

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vicky7230.flux.R
import com.vicky7230.flux.data.network.model.genres.Genre
import com.vicky7230.flux.utils.AppConstants
import kotlinx.android.synthetic.main.discover_genre_list_item.view.*

/**
 * Created by vicky on 15/3/18.
 */
class DiscoverAdapter(private val genres: MutableList<Genre>?) :
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

    private fun getItem(position: Int): Genre? {
        return if (position != RecyclerView.NO_POSITION)
            genres?.get(position)
        else
            null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val genreViewHolder = DiscoverGenreViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.discover_genre_list_item,
                        parent,
                        false
                )
        )

        genreViewHolder.itemView.setOnClickListener({
            val genre = getItem(genreViewHolder.adapterPosition)
            if (genre != null) {
            }
        })

        return genreViewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DiscoverGenreViewHolder).onBind(position)
    }

    inner class DiscoverGenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(position: Int) {
            val genre: Genre? = genres?.get(position)
            itemView.genre_title.text = AppConstants.genres[genre?.id]?.toUpperCase()

            when (genre?.id) {
                10759 -> itemView.genre_image.setImageResource(R.drawable.action_adevnture)
                16 -> itemView.genre_image.setImageResource(R.drawable.animation)
                35 -> itemView.genre_image.setImageResource(R.drawable.comedy)
                80 -> itemView.genre_image.setImageResource(R.drawable.crime)
                99 -> itemView.genre_image.setImageResource(R.drawable.documentary)
                18 -> itemView.genre_image.setImageResource(R.drawable.drama)
                10751 -> itemView.genre_image.setImageResource(R.drawable.family)
                10762 -> itemView.genre_image.setImageResource(R.drawable.kids)
                9648 -> itemView.genre_image.setImageResource(R.drawable.mystery)
                10763 -> itemView.genre_image.setImageResource(R.drawable.news)
                10764 -> itemView.genre_image.setImageResource(R.drawable.reality)
                10765 -> itemView.genre_image.setImageResource(R.drawable.scifi)
                10766 -> itemView.genre_image.setImageResource(R.drawable.soap)
                10767 -> itemView.genre_image.setImageResource(R.drawable.talk)
                10768 -> itemView.genre_image.setImageResource(R.drawable.war_politics)
                37 -> itemView.genre_image.setImageResource(R.drawable.western)
                28 -> itemView.genre_image.setImageResource(R.drawable.action)
                12 -> itemView.genre_image.setImageResource(R.drawable.adventure)
                14 -> itemView.genre_image.setImageResource(R.drawable.fantasy)
                36 -> itemView.genre_image.setImageResource(R.drawable.history)
                27 -> itemView.genre_image.setImageResource(R.drawable.horror)
                10402 -> itemView.genre_image.setImageResource(R.drawable.music)
                10749 -> itemView.genre_image.setImageResource(R.drawable.romance)
                878 -> itemView.genre_image.setImageResource(R.drawable.science_fiction)
                10770 -> itemView.genre_image.setImageResource(R.drawable.tv_movie)
                53 -> itemView.genre_image.setImageResource(R.drawable.thriller)
                10752 -> itemView.genre_image.setImageResource(R.drawable.war)
            }
        }
    }

}