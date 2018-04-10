package com.vicky7230.flux.ui.home.profile

import android.annotation.SuppressLint
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.vicky7230.flux.R
import com.vicky7230.flux.data.network.model.results.Result
import com.vicky7230.flux.ui.home.ResultDiffUtilCallback
import com.vicky7230.flux.utils.GlideApp
import kotlinx.android.synthetic.main.favourite_list_item.view.*
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by vicky on 27/2/18.
 */
class FavouriteAdapter(private val resultList: MutableList<Result>?) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TYPE_LOADING = -1
    private val TYPE_RESULT = 1

    interface Callback {
        fun onTvShowClick(id: Int)
    }

    private var callback: Callback? = null

    fun setCallback(callback: Callback) {
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
                        R.layout.favourite_list_item,
                        parent,
                        false
                )
        )

        resultViewHolder.itemView.setOnClickListener({
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
        @SuppressLint("SetTextI18n")
        fun onBind(result: Result?) {
            GlideApp
                    .with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w342" + result?.posterPath)
                    .transition(withCrossFade())
                    .fitCenter()
                    .into(itemView.favourite_tv_image)

            itemView.favourite_tv_name.text = result?.originalName

            val sdf = SimpleDateFormat("yyyy-dd-MM", Locale.getDefault())
            val sdf2 = SimpleDateFormat("dd MMM yy", Locale.getDefault())
            itemView.favourite_date_and_rating.text = "" +
                    "${
                    if (result?.firstAirDate != null && result.firstAirDate?.isNotEmpty()!!)
                        sdf2.format(sdf.parse(result.firstAirDate))
                    else "Unknown"
                    }    ${result?.voteAverage}/10"
            itemView.favourite_tv_overview.text = result?.overview
        }
    }

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind() {
        }
    }
}