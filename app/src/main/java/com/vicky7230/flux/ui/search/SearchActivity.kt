package com.vicky7230.flux.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.inputmethod.EditorInfo
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.vicky7230.flux.R
import com.vicky7230.flux.data.network.model.results.Result
import com.vicky7230.flux.ui.base.BaseActivity
import com.vicky7230.flux.ui.tvDetails.TvDetailsActivity
import com.vicky7230.flux.utils.KeyboardUtils
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject


class SearchActivity : BaseActivity(), SearchMvpView, TvSearchAdapter.Callback {

    @Inject
    lateinit var presenter: SearchMvpPresenter<SearchMvpView>
    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager
    @Inject
    lateinit var tvSearchAdapter: TvSearchAdapter

    var isLoading = false

    companion object {
        fun getStartIntent(context: Context) = Intent(context, SearchActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        presenter.onAttach(this)
        tvSearchAdapter.setCallback(this)
        init()
    }

    private fun init() {

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        RxView.clicks(back_button).subscribe({
            finish()
        })

        RxView.clicks(clear_button).subscribe({
            search_text.setText("")
            KeyboardUtils.showSoftInput(search_text, this)
        })

        search_list.layoutManager = linearLayoutManager
        search_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        search_list.adapter = tvSearchAdapter

        search_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = linearLayoutManager.childCount
                val totalItemCount = linearLayoutManager.itemCount
                val pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + pastVisibleItems >= totalItemCount && !isLoading) {
                    tvSearchAdapter.addItem(
                            Result(
                                    type = "LOADING"
                            )
                    )
                    presenter.getSearchResults(search_text.text.toString())
                    isLoading = true
                }
            }
        })

        RxTextView.editorActionEvents(search_text).subscribe({ t ->
            if (t.actionId() == EditorInfo.IME_ACTION_SEARCH) {
                tvSearchAdapter.clearItems()
                presenter.resetPageVariable()
                presenter.getSearchResults(search_text.text.toString())
            }
        })
    }

    override fun updateSearchList(results: MutableList<Result>) {
        if (tvSearchAdapter.itemCount > 0)
            tvSearchAdapter.removeItem()
        if (results.size > 0) {
            tvSearchAdapter.addItems(results)
            isLoading = false
        }
    }

    override fun onTvShowClick(id: Int) {
        startActivity(TvDetailsActivity.getStartIntent(this, id.toString()))
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}
