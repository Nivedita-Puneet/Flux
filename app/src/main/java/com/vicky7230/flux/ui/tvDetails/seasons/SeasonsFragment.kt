package com.vicky7230.flux.ui.tvDetails.seasons


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vicky7230.flux.R
import com.vicky7230.flux.data.network.model.tvDetails.Season
import com.vicky7230.flux.ui.base.BaseFragment
import com.vicky7230.flux.utils.AppConstants
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_seasons.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class SeasonsFragment : BaseFragment(), SeasonsMvpView {

    @Inject
    lateinit var presenter: SeasonsMvpPresenter<SeasonsMvpView>
    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager
    @Inject
    lateinit var seasonsAdapter: SeasonsAdapter

    companion object {
        fun newInstance(seasons: ArrayList<Season>?): SeasonsFragment {
            val seasonsFragment = SeasonsFragment()
            val args = Bundle()
            args.putParcelableArrayList(AppConstants.SEASON_LIST, seasons)
            seasonsFragment.arguments = args
            return seasonsFragment
        }
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_seasons, container, false)
        presenter.onAttach(this)
        return view
    }

    override fun setUp(view: View) {
        if (arguments != null) {
            season_list.layoutManager = linearLayoutManager
            season_list.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            season_list.adapter = seasonsAdapter
            seasonsAdapter.addItems(arguments?.getParcelableArrayList<Season>(AppConstants.SEASON_LIST))
        }
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }
}// Required empty public constructor
