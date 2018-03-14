package com.vicky7230.flux.ui.home.discover

import com.vicky7230.flux.data.Config
import com.vicky7230.flux.data.DataManager
import com.vicky7230.flux.data.network.model.genres.Genre
import com.vicky7230.flux.data.network.model.genres.Genres
import com.vicky7230.flux.ui.base.BasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by vicky on 15/3/18.
 */
class DiscoverPresenter<V : DiscoverMvpView> @Inject constructor(
    private val dataManager: DataManager,
    private val compositeDisposable: CompositeDisposable
) : BasePresenter<V>(dataManager, compositeDisposable), DiscoverMvpPresenter<V> {

    override fun getGenresList() {
        mvpView?.showLoading()
        compositeDisposable.add(
            Observable.zip(
                dataManager.getGenresTv(Config.API_KEY),
                dataManager.getGenresMovies(Config.API_KEY),
                BiFunction { t1: Genres, t2: Genres ->
                    val data = mutableListOf<Genre>()
                    data.addAll(t1.genres ?: arrayListOf())
                    data.addAll(t2.genres ?: arrayListOf())
                    data.sortBy { it.name }
                    return@BiFunction data.distinctBy { it.name } as MutableList<Genre>
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ genres: MutableList<Genre>? ->
                    if (!isViewAttached())
                        return@subscribe
                    mvpView?.hideLoading()
                    if (genres != null)
                        mvpView?.showGenres(genres)
                }, { throwable ->
                    if (!isViewAttached())
                        return@subscribe
                    mvpView?.hideLoading()
                    mvpView?.showMessage(throwable.message!!)
                    Timber.e(throwable.message)
                })
        )
    }

}