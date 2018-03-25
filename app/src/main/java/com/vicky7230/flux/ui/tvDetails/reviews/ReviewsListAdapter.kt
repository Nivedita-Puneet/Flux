package com.vicky7230.flux.ui.tvDetails.reviews

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vicky7230.flux.R
import com.vicky7230.flux.data.network.model.tvDetails.ReviewResult
import kotlinx.android.synthetic.main.review_list_item.view.*

/**
 * Created by vicky on 25/3/18.
 */
class ReviewsListAdapter(private val reviews: MutableList<ReviewResult>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun addItems(reviews: MutableList<ReviewResult>?) {
        this.reviews.addAll(reviews!!)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ReviewViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.review_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ReviewViewHolder).onBind(position)
    }

    inner class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {
            val reviewResult: ReviewResult? = reviews[position]
            if (reviewResult != null) {
                itemView.author.text = reviewResult.author
                itemView.comment.text = reviewResult.content
            }
        }
    }
}