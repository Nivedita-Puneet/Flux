package com.vicky7230.flux.ui.tvDetails.reviews


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vicky7230.flux.R
import com.vicky7230.flux.data.network.model.tvDetails.ReviewResult
import com.vicky7230.flux.ui.base.BaseFragment
import com.vicky7230.flux.utils.AppConstants
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_reviews.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class ReviewsFragment : BaseFragment(), ReviewsMvpView {

    @Inject
    lateinit var presenter: ReviewsMvpPresenter<ReviewsMvpView>
    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager
    @Inject
    lateinit var reviewsListAdapter: ReviewsListAdapter

    companion object {
        fun newInstance(reviews: ArrayList<ReviewResult>?): ReviewsFragment {
            val reviewsFragment = ReviewsFragment()
            val args = Bundle()
            args.putParcelableArrayList(AppConstants.REVIEWS_LIST, reviews)
            reviewsFragment.arguments = args
            return reviewsFragment
        }
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_reviews, container, false)
        presenter.onAttach(this)
        return view
    }

    override fun setUp(view: View) {
        if (arguments != null) {

            reviews_list.layoutManager = linearLayoutManager
            reviews_list.adapter = reviewsListAdapter
            reviewsListAdapter.addItems(arguments?.getParcelableArrayList<ReviewResult>(AppConstants.REVIEWS_LIST))
        }
    }

}
