package com.vicky7230.flux.ui.home.tv

import com.vicky7230.flux.data.Config
import com.vicky7230.flux.data.DataManager
import com.vicky7230.flux.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by vicky on 27/2/18.
 */
class TvPresenter<V : TvMvpView> @Inject constructor(
    private val dataManager: DataManager,
    private val compositeDisposable: CompositeDisposable
) : BasePresenter<V>(dataManager, compositeDisposable), TvMvpPresenter<V> {

    private var page = 1

    override fun getTvs() {
        compositeDisposable.add(
            dataManager.getTvByGenres(
                Config.API_KEY,
                dataManager.getUserGenres()!!,
                page.toString()
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ results ->
                    if (!isViewAttached())
                        return@subscribe
                    mvpView?.hideLoading()
                    if (results.results != null) {
                        mvpView?.updateTvList(results.results!!)
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