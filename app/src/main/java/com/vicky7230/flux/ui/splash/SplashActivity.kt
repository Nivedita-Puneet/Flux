package com.vicky7230.flux.ui.splash

import android.os.Bundle
import com.vicky7230.flux.R
import com.vicky7230.flux.ui.base.BaseActivity
import com.vicky7230.flux.ui.genres.GenresActivity
import com.vicky7230.flux.ui.home.HomeActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashMvpView {

    @Inject
    lateinit var presenter: SplashMvpPresenter<SplashMvpView>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter.onAttach(this)
        init()
    }

    private fun init() {
        presenter.getConfigurations()
    }

    override fun gotToGenreScreen() {
        startActivity(GenresActivity.getStartIntent(this))
        finish()
    }

    override fun goToHomeScreen() {
        startActivity(HomeActivity.getStartIntent(this))
        finish()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}
