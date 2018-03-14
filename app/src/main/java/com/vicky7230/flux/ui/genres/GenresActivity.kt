package com.vicky7230.flux.ui.genres

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.vicky7230.flux.R
import com.vicky7230.flux.data.network.model.genres.Genre
import com.vicky7230.flux.ui.base.BaseActivity
import com.vicky7230.flux.ui.home.HomeActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_genres.*
import javax.inject.Inject

class GenresActivity : BaseActivity(), GenresMvpView {

    @Inject
    lateinit var presenter: GenresMvpPresenter<GenresMvpView>
    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager
    @Inject
    lateinit var genresAdapter: GenresAdapter

    companion object {
        fun getStartIntent(context: Context) = Intent(context, GenresActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genres)
        presenter.onAttach(this)
        init()
    }

    private fun init() {

        setSupportActionBar(toolbar as Toolbar?)

        genre_list.layoutManager = linearLayoutManager
        genre_list.adapter = genresAdapter

        presenter.getGenres()
    }

    override fun showGenres(genres: MutableList<Genre>?) {
        genresAdapter.addItems(genres)
    }

    override fun goToHome() {
        startActivity(HomeActivity.getStartIntent(this))
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_genres, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_ok -> {
                val genreIds = StringBuilder()

                for (i in 0 until genresAdapter.itemCount) {
                    if (genresAdapter.getItem(i)?.selected!!) {
                        genreIds.append(genresAdapter.getItem(i)?.id)
                        genreIds.append("|")
                    }
                }

                if (genreIds.toString().isEmpty())
                    showError("Please check at least 1")
                else
                    presenter.saveGenres(genreIds.toString().dropLast(1))

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}
