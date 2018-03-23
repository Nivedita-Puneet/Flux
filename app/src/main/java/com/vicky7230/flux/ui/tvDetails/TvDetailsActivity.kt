package com.vicky7230.flux.ui.tvDetails

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.view.Menu
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.vicky7230.flux.R
import com.vicky7230.flux.data.network.model.tvDetails.TvDetails
import com.vicky7230.flux.ui.base.BaseActivity
import com.vicky7230.flux.ui.youtubePlayer.YoutubeActivity
import com.vicky7230.flux.utils.AppConstants
import com.vicky7230.flux.utils.GlideApp
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

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewpager.offscreenPageLimit = 3
        viewpager.adapter = ViewsPagerAdapter(supportFragmentManager)

        tabs.setupWithViewPager(viewpager)

        if (intent != null && intent.getStringExtra(AppConstants.TV_ID) != null) {
            presenter.getTvDetails(intent.getStringExtra(AppConstants.TV_ID))
        }
    }

    @SuppressLint("SetTextI18n")
    override fun showDetails(tvDetails: TvDetails?) {

        app_bar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            internal var isShow = true
            internal var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    collapse_toolbar.title = tvDetails?.name
                    isShow = true
                } else if (isShow) {
                    collapse_toolbar.title = " "
                    isShow = false
                }
            }
        })

        supportActionBar?.setDisplayShowTitleEnabled(true)

        GlideApp
                .with(this)
                .load("https://image.tmdb.org/t/p/w1280/" + tvDetails?.backdropPath)
                .transition(withCrossFade())
                .centerCrop()
                .into(tv_series_image)

        /*GlideApp
                .with(this)
                .load("https://image.tmdb.org/t/p/w500/" + tvDetails?.networks?.get(0)?.logoPath)
                .transition(withCrossFade())
                .centerInside()
                .into(network_logo)*/

        collapse_toolbar.title = tvDetails?.name
        tv_series_title.text = tvDetails?.name
        year.text = tvDetails?.firstAirDate?.substring(0, 4)
        runtime.text = "${tvDetails?.episodeRunTime?.get(0).toString()} minutes"
        seasons.text = "${tvDetails?.numberOfSeasons.toString()} Seasons"
        rating.rating = ((tvDetails?.voteAverage?.div(10.0f) ?: 0.0f) * 5.0f)
        rating_float.text = tvDetails?.voteAverage.toString()


        val key = tvDetails?.videos?.results?.find { result ->
            result.type.equals("Trailer")
        }?.key

        play_trailer.setOnClickListener({
            startActivity(YoutubeActivity.getStartIntent(this, key))
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_tv_details, menu)
        return true
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}
