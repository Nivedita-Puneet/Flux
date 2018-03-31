package com.vicky7230.flux.ui.home.discover


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup

import com.vicky7230.flux.R
import com.vicky7230.flux.data.network.model.genres.Genre
import com.vicky7230.flux.ui.base.BaseFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_discover.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class DiscoverFragment : BaseFragment(), DiscoverMvpView {

    @Inject
    lateinit var presenter: DiscoverMvpPresenter<DiscoverMvpView>
    @Inject
    lateinit var gridLayoutManager: GridLayoutManager
    @Inject
    lateinit var discoverItemOffsetDecoration: DiscoverItemOffsetDecoration
    @Inject
    lateinit var discoverAdapter: DiscoverAdapter

    companion object {
        fun newInstance() = DiscoverFragment()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_discover, container, false)
        presenter.onAttach(this)
        return view
    }

    override fun setUp(view: View) {

        genres_list.layoutManager = gridLayoutManager
        genres_list.setHasFixedSize(true)
        genres_list.addItemDecoration(discoverItemOffsetDecoration)
        genres_list.adapter = discoverAdapter

        presenter.getGenresList()
    }

    override fun showGenres(genres: MutableList<Genre>) {
        progress.visibility = GONE
        genres_list.visibility = VISIBLE
        discoverAdapter.addItems(genres)
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }
}
