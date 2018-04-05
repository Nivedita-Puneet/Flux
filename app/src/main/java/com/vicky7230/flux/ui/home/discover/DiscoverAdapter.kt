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
    /*val genreMap = hashMapOf<Int, Int>(
            10759 to R.drawable.action_adevnture,
            16 to R.drawable.animation,
            35 to R.drawable.comedy,
            80 to R.drawable.crime,
            99 to R.drawable.documentary,
            18 to R.drawable.drama,
            10751 to R.drawable.family,
            10762 to R.drawable.kids,
            9648 to R.drawable.mystery,
            10763 to R.drawable.news,
            10764 to R.drawable.reality,
            10765 to R.drawable.scifi,
            10766 to R.drawable.soap,
            10767 to R.drawable.talk,
            10768 to R.drawable.war_politics,
            37 to R.drawable.western,
            28 to R.drawable.action,
            12 to R.drawable.adventure,
            14 to R.drawable.fantasy,
            36 to R.drawable.history,
            27 to R.drawable.horror,
            10402 to R.drawable.music,
            10749 to R.drawable.romance,
            878 to R.drawable.science_fiction,
            10770 to R.drawable.tv_movie,
            53 to R.drawable.thriller,
            10752 to R.drawable.war
    )*/

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
            if (genre != null) {
                itemView.genre_title.text = AppConstants.genres[genre.id]?.toUpperCase()
                //genreMap[genre.id ?: 0]?.let { itemView.genre_image.setImageResource(it) }
                val colors = itemView.genre_card.context.resources.getIntArray(R.array.shades_of_gray)
                itemView.genre_card.setBackgroundColor(colors[position % 4])
            }
        }
    }

}