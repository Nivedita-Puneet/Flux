package com.vicky7230.flux.ui.tvDetails.seasons

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vicky7230.flux.R
import com.vicky7230.flux.data.network.model.tvDetails.Season
import com.vicky7230.flux.utils.GlideApp
import kotlinx.android.synthetic.main.season_list_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class SeasonsAdapter(private val seasons: MutableList<Season>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SeasonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.season_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return seasons.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SeasonViewHolder).onBind(position)
    }

    fun addItems(seasons: ArrayList<Season>?) {
        this.seasons.addAll(seasons!!)
        notifyDataSetChanged()
    }

    inner class SeasonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun onBind(position: Int) {
            val season: Season? = seasons[position]
            if (season != null) {
                GlideApp
                        .with(itemView.context)
                        .load("https://image.tmdb.org/t/p/w780" + season.posterPath)
                        .centerInside()
                        .into(itemView.season_image)
                itemView.season_number.text = "Season ${season.seasonNumber.toString()}"
                val sdf = SimpleDateFormat("yyyy-dd-MM", Locale.getDefault())
                val sdf2 = SimpleDateFormat("dd MMM yy", Locale.getDefault())
                itemView.date_and_episode_count.text = "" +
                        "${
                        if (season.airDate != null)
                            sdf2.format(sdf.parse(season.airDate))
                        else "Unknown"
                        }    ${season.episodeCount} Episodes"
                itemView.season_overview.text = season.overview
            }
        }
    }
}