package com.vicky7230.flux.ui.search

import com.vicky7230.flux.data.Config
import com.vicky7230.flux.data.DataManager
import com.vicky7230.flux.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class SearchPresenter<V : SearchMvpView> @Inject constructor(
        private val dataManager: DataManager,
        private val compositeDisposable: CompositeDisposable
) : BasePresenter<V>(dataManager, compositeDisposable), SearchMvpPresenter<V> {

    var page = 1

    override fun resetPageVariable() {
        page = 1
    }

    override fun getSearchResults(query: String) {
        if (page == 1)
            mvpView?.showLoading()
        compositeDisposable.add(
                dataManager.getSearchResults(
                        Config.API_KEY,
                        query,
                        page.toString()
                )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ results ->
                            if (!isViewAttached())
                                return@subscribe
                            mvpView?.hideLoading()
                            if (results.results != null) {
                                mvpView?.updateSearchList(results.results!!)
                                ++page
                            }
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
