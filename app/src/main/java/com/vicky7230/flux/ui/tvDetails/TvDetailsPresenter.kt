package com.vicky7230.flux.ui.tvDetails

import com.vicky7230.flux.data.Config
import com.vicky7230.flux.data.DataManager
import com.vicky7230.flux.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by vicky on 21/3/18.
 */

class TvDetailsPresenter<V : TvDetailsMvpView> @Inject constructor(
        private val dataManager: DataManager,
        private val compositeDisposable: CompositeDisposable
) : BasePresenter<V>(dataManager, compositeDisposable), TvDetailsMvpPresenter<V> {

    override fun getTvDetails(tvId: String) {

        mvpView?.showLoading()
        compositeDisposable.add(
                dataManager.getTvDetails(
                        tvId,
                        Config.API_KEY)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ tvDetails ->
                            if (!isViewAttached())
                                return@subscribe
                            mvpView?.hideLoading()
                            if (tvDetails != null) {
                                mvpView?.showDetails(tvDetails)
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