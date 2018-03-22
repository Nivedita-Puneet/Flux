package com.vicky7230.flux.ui.tvDetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.vicky7230.flux.R
import com.vicky7230.flux.ui.base.BaseActivity
import com.vicky7230.flux.utils.AppConstants
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_tv_details.*
import javax.inject.Inject

class TvDetailsActivity : BaseActivity(), TvDetailsMvpView {

    @Inject
    lateinit var presenter: TvDetailsMvpPresenter<TvDetailsMvpView>

    companion object {
        fun getStartIntent(context: Context, tvId: String): Intent {
            val intent = Intent(context, TvDetailsActivity::class.java)
            intent.putExtra(AppConstants.TV_ID, tvId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_details)
        presenter.onAttach(this)
        init()
    }

    private fun init() {

        setSupportActionBar(htab_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        htab_viewpager.offscreenPageLimit = 3
        htab_viewpager.adapter = ViewsPagerAdapter(supportFragmentManager)

        htab_tabs.setupWithViewPager(htab_viewpager)



        if (intent != null && intent.getStringExtra(AppConstants.TV_ID) != null) {
            presenter.getTvDetails(intent.getStringExtra(AppConstants.TV_ID))
        }
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}
