package com.vicky7230.flux.ui.home.watchlist

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.vicky7230.flux.R
import com.vicky7230.flux.data.network.model.results.Result
import com.vicky7230.flux.ui.home.ResultDiffUtilCallback
import com.vicky7230.flux.utils.AppConstants
import com.vicky7230.flux.utils.GlideApp
import kotlinx.android.synthetic.main.tv_list_item.view.*


/**
 * Created by vicky on 27/2/18.
 */
class WatchListAdapter(private val resultList: MutableList<Result>?) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_LOADING = -1
    private val TYPE_RESULT = 1

    interface Callback {
        fun onTvShowClick(id: Int)
    }

    private var callback: Callback? = null

    fun setCallback(callback: WatchListFragment) {
        this.callback = callback
    }

    fun addItems(resultList: MutableList<Result>?) {

        val newResultList = ArrayList<Result>()
        newResultList.addAll(this.resultList!!)
        newResultList.addAll(resultList!!)

        val diffResult =
                DiffUtil.calculateDiff(
                        ResultDiffUtilCallback(
                                this.resultList,
                                newResultList
                        )
                )
        this.resultList.addAll(resultList)
        diffResult.dispatchUpdatesTo(this)
    }

    fun clearItems() {
        resultList?.clear()
        notifyDataSetChanged()
    }

    fun addItem(recipe: Result?) {
        resultList?.add(recipe!!)
        notifyItemInserted(resultList!!.size - 1)
    }

    fun removeItem() {
        resultList?.removeAt(resultList.size - 1)
        notifyItemRemoved(resultList!!.size)
    }

    override fun getItemViewType(position: Int): Int {
        return if (resultList?.get(position)?.type == "LOADING") TYPE_LOADING else TYPE_RESULT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_LOADING -> createLoadingViewHolder(parent)
            else -> createResultViewHolder(parent)
        }
    }

    private fun createResultViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {

        val resultViewHolder = ResultViewHolder(
                LayoutInflater.from(parent?.context).inflate(
                        R.layout.watch_list_item,
                        parent,
                        false
                )
        )

        resultViewHolder.itemView.tv_image_card.setOnClickListener({
            val result = getItem(resultViewHolder.adapterPosition)
            if (result != null) {
                callback?.onTvShowClick(result.id ?: 0)
            }
        })

        return resultViewHolder
    }


    private fun createLoadingViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        return LoadingViewHolder(
                LayoutInflater.from(parent?.context).inflate(
                        R.layout.tv_list_footer,
                        parent,
                        false
                )
        )
    }

    fun getItem(position: Int): Result? {
        return if (position != RecyclerView.NO_POSITION)
            resultList?.get(position)
        else
            null
    }

    override fun getItemCount(): Int {
        return resultList?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_RESULT -> (holder as ResultViewHolder).onBind(resultList?.get(position))
            TYPE_LOADING -> (holder as LoadingViewHolder).onBind()
        }
    }

    class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(result: Result?) {
            GlideApp
                    .with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500" + result?.posterPath)
                    .transition(withCrossFade())
                    .fitCenter()
                    .into(itemView.tv_image)

            itemView.tv_title.text = result?.originalName

            var genres = ""
            result?.genreIds?.forEach { t -> genres = genres.plus(AppConstants.genres[t] + ", ") }
            itemView.tv_genres.text = genres.dropLast(2)

            itemView.rating_circle.text = result?.voteAverage.toString()
        }
    }

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind() {
        }
    }
}