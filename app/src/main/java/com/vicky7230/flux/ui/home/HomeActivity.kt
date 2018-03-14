package com.vicky7230.flux.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import com.vicky7230.flux.R
import com.vicky7230.flux.ui.base.BaseActivity
import com.vicky7230.flux.ui.home.discover.DiscoverFragment
import com.vicky7230.flux.ui.home.playlist.PlaylistFragment
import com.vicky7230.flux.ui.home.profile.ProfileFragment
import com.vicky7230.flux.ui.home.tv.TvFragment
import com.vicky7230.flux.utils.BottomNavigationViewHelper
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject


class HomeActivity : BaseActivity(), HomeMvpView, HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var presenter: HomeMvpPresenter<HomeMvpView>

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        presenter.onAttach(this)
        init()
    }

    @SuppressLint("SetTextI18n")
    private fun init() {

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView)

        bottomNavigationView.setOnNavigationItemSelectedListener({
            val fragment: Fragment
            when (it.itemId) {
                R.id.tv -> {
                    fragment = TvFragment.newInstance()
                    loadFragment(fragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.discover -> {
                    fragment = DiscoverFragment.newInstance()
                    loadFragment(fragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.playlist -> {
                    fragment = PlaylistFragment.newInstance()
                    loadFragment(fragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.profile -> {
                    fragment = ProfileFragment.newInstance()
                    loadFragment(fragment)
                    return@setOnNavigationItemSelectedListener true
                }
            }

            false
        })

        bottomNavigationView.selectedItemId = R.id.tv
        //loadFragment(TvFragment.newInstance())
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, fragment)
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_home, menu)
        return true
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}
