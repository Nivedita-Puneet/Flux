package com.vicky7230.flux.ui.home.tv


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.vicky7230.flux.R
import com.vicky7230.flux.data.network.model.results.Result
import com.vicky7230.flux.ui.base.BaseFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_tv.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class TvFragment : BaseFragment(), TvMvpView {

    @Inject
    lateinit var presenter: TvMvpPresenter<TvMvpView>
    @Inject
    lateinit var gridLayoutManager: GridLayoutManager
    @Inject
    lateinit var itemOffsetDecoration: ItemOffsetDecoration
    @Inject
    lateinit var tvAdapter: TvAdapter

    var isLoading = false

    companion object {
        fun newInstance() = TvFragment()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tv, container, false)
        presenter.onAttach(this)
        return view
    }

    override fun setUp(view: View) {

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (tvAdapter.getItem(position)?.type) {
                    "LOADING" -> 2
                    "RESULT" -> 1
                    else -> 1
                }
            }
        }

        tvtList.layoutManager = gridLayoutManager
        tvtList.addItemDecoration(itemOffsetDecoration)
        tvtList.adapter = tvAdapter

        tvtList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = gridLayoutManager.childCount
                val totalItemCount = gridLayoutManager.itemCount
                val pastVisibleItems = gridLayoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + pastVisibleItems >= totalItemCount && !isLoading) {
                    tvAdapter.addItem(
                        Result(
                            type = "LOADING"
                        )
                    )
                    presenter.getTvs()
                    isLoading = true
                }
            }
        })

        showLoading()
        presenter.getTvs()
    }

    override fun updateTvList(results: MutableList<Result>) {
        if (tvAdapter.itemCount > 0)
            tvAdapter.removeItem()
        if (results.size > 0) {
            tvAdapter.addItems(results)
            isLoading = false
        }
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }
}
