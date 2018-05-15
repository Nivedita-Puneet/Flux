package com.vicky7230.flux.ui.home.watchlist

import com.vicky7230.flux.data.Config
import com.vicky7230.flux.data.DataManager
import com.vicky7230.flux.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class WatchListPresenter<V : WatchListMvpView> @Inject constructor(
        private val dataManager: DataManager,
        private val compositeDisposable: CompositeDisposable
) : BasePresenter<V>(dataManager, compositeDisposable), WatchListMvpPresenter<V> {

    var page = 1

    override fun resetPageVariable() {
        page = 1
    }

    override fun getWatchList() {
        if (dataManager.getIsUserLoggedIn()) {
            compositeDisposable.add(
                    dataManager.getWatchList(
                            dataManager.getAccountId() ?: 0,
                            Config.API_KEY,
                            dataManager.getSessionIdFromPreference() ?: "",
                            page.toString())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({ results ->
                                if (!isViewAttached())
                                    return@subscribe
                                if (results.results != null) {
                                    mvpView?.showWatchList(results.results ?: arrayListOf())
                                    ++page
                                }
                            }, { throwable ->
                                if (!isViewAttached())
                                    return@subscribe
                                handleApiError(throwable)
                                Timber.i(throwable)
                            })
            )
        }
    }
}