package com.vicky7230.flux.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.vicky7230.flux.R
import com.vicky7230.flux.ui.base.BaseActivity
import com.vicky7230.flux.ui.login.LoginActivity
import com.vicky7230.flux.utils.BottomNavigationViewHelper
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_home.*
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject


class HomeActivity : BaseActivity(), HomeMvpView, HasSupportFragmentInjector {

    private val LOGIN_REQUEST: Int = 802

    val PLAYLIST = "playlist"
    val PROFILE = "profile"

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var presenter: HomeMvpPresenter<HomeMvpView>
    @Inject
    lateinit var viewPagerAdapter: ViewPagerAdapter

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

        viewPager.offscreenPageLimit = 3
        viewPager.adapter = viewPagerAdapter

        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView)

        bottomNavigationView.setOnNavigationItemSelectedListener({
            when (it.itemId) {
                R.id.tv -> {
                    viewPager.setCurrentItem(0, true)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.discover -> {
                    viewPager.setCurrentItem(1, true)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.playlist -> {
                    presenter.checkIfUserLoggedIn(PLAYLIST)
                    //return@setOnNavigationItemSelectedListener true
                }
                R.id.profile -> {
                    presenter.checkIfUserLoggedIn(PROFILE)
                    //return@setOnNavigationItemSelectedListener true
                }
            }

            false
        })

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                bottomNavigationView.menu.getItem(position).isChecked = true
            }
        })

        bottomNavigationView.selectedItemId = R.id.tv
        //loadFragment(TvFragment.newInstance())
    }

    override fun changeFragment(fragment: String) {
        when (fragment) {
            PLAYLIST -> {
                viewPager.setCurrentItem(2, true)
                bottomNavigationView.menu.getItem(2).isChecked = true
            }
            PROFILE -> {
                viewPager.setCurrentItem(3, true)
                bottomNavigationView.menu.getItem(3).isChecked = true
            }
        }
    }

    override fun showLoginScreen() {
        startActivityForResult(LoginActivity.getStartIntent(this), LOGIN_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == LOGIN_REQUEST) {
            if (resultCode == RESULT_OK) {
                EventBus.getDefault().postSticky(LoginSuccessfulEvent())
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search -> {
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show()
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
