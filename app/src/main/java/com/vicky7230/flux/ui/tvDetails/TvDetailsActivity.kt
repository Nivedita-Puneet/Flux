package com.vicky7230.flux.ui.tvDetails

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.vicky7230.flux.R
import com.vicky7230.flux.data.network.model.tvDetails.Genre
import com.vicky7230.flux.data.network.model.tvDetails.TvDetails
import com.vicky7230.flux.ui.base.BaseActivity
import com.vicky7230.flux.ui.tvDetails.cast.CastFragment
import com.vicky7230.flux.ui.tvDetails.info.InfoFragment
import com.vicky7230.flux.ui.tvDetails.seasons.SeasonsFragment
import com.vicky7230.flux.ui.youtubePlayer.YoutubeActivity
import com.vicky7230.flux.utils.AppConstants
import com.vicky7230.flux.utils.GlideApp
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_tv_details.*
import javax.inject.Inject


class TvDetailsActivity : BaseActivity(), TvDetailsMvpView, HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var presenter: TvDetailsMvpPresenter<TvDetailsMvpView>
    @Inject
    lateinit var tvDetailsPagerAdapter: DetailsPagerAdapter

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

        tabs.setupWithViewPager(viewpager)

        if (intent != null && intent.getStringExtra(AppConstants.TV_ID) != null) {
            presenter.getTvDetails(intent.getStringExtra(AppConstants.TV_ID))
        }
    }

    override fun showDetails(tvDetails: TvDetails?) {

        setUpAppBar(tvDetails)

        displayTvDetails(tvDetails)

        play_trailer.setOnClickListener({
            val key = tvDetails?.videos?.results?.find { result ->
                result.type.equals("Trailer")
            }?.key
            startActivity(YoutubeActivity.getStartIntent(this, key))
        })

        addDetailFragments(tvDetails)

    }

    private fun setUpAppBar(tvDetails: TvDetails?) {
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
                    tv_basic_details.visibility = GONE
                } else if (isShow) {
                    collapse_toolbar.title = " "
                    isShow = false
                    tv_basic_details.visibility = VISIBLE
                }
            }
        })

        supportActionBar?.setDisplayShowTitleEnabled(true)
    }

    @SuppressLint("SetTextI18n")
    private fun displayTvDetails(tvDetails: TvDetails?) {
        GlideApp
                .with(this)
                .load("https://image.tmdb.org/t/p/w1280/" + tvDetails?.backdropPath)
                .transition(withCrossFade())
                .centerCrop()
                .into(tv_series_image)

        collapse_toolbar.title = tvDetails?.name
        tv_series_title.text = tvDetails?.name
        year.text = tvDetails?.firstAirDate?.substring(0, 4)
        runtime.text = "${tvDetails?.episodeRunTime?.get(0).toString()} minutes"
        seasons.text = "${tvDetails?.numberOfSeasons.toString()} Seasons"
        rating.rating = ((tvDetails?.voteAverage?.div(10.0f) ?: 0.0f) * 5.0f)
        rating_float.text = tvDetails?.voteAverage.toString()
    }

    private fun addDetailFragments(tvDetails: TvDetails?) {
        val fragments = mutableListOf<Fragment>()
        fragments.add(
                InfoFragment.newInstance(
                        tvDetails?.networks?.get(0)?.logoPath,
                        tvDetails?.numberOfSeasons,
                        tvDetails?.numberOfEpisodes,
                        tvDetails?.genres as ArrayList<Genre>?,
                        tvDetails?.overview
                )
        )
        fragments.add(SeasonsFragment.newInstance())
        fragments.add(CastFragment.newInstance())

        viewpager.adapter = tvDetailsPagerAdapter
        tvDetailsPagerAdapter.addItems(fragments)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_tv_details, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}
