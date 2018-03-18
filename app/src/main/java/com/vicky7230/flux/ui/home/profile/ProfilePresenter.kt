package com.vicky7230.flux.ui.home.profile

import com.vicky7230.flux.data.Config
import com.vicky7230.flux.data.DataManager
import com.vicky7230.flux.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by vicky on 18/3/18.
 */
class ProfilePresenter<V : ProfileMvpView> @Inject constructor(
    private val dataManager: DataManager,
    private val compositeDisposable: CompositeDisposable
) : BasePresenter<V>(dataManager, compositeDisposable), ProfileMvpPresenter<V> {

    override fun getAccountDetails() {

        compositeDisposable.add(
            dataManager.getAccountDetails(
                Config.API_KEY,
                dataManager.getSessionIdFromPreference() ?: ""
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ account ->
                    if (!isViewAttached())
                        return@subscribe
                    //mvpView?.hideLoading()
                    if (account != null) {
                        mvpView?.showAccountDetails(account)
                    }
                }, { throwable ->
                    if (!isViewAttached())
                        return@subscribe
                    //mvpView?.hideLoading()
                    mvpView?.showMessage(throwable.message!!)
                    Timber.e(throwable.message)
                })

        )
    }

}