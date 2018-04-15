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

    var page = 1

    override fun resetPageVariable() {
        page = 1
    }

    override fun getAccountDetails() {
        if (dataManager.getIsUserLoggedIn()) {
            compositeDisposable.add(
                    dataManager.getAccountDetails(
                            Config.API_KEY,
                            dataManager.getSessionIdFromPreference() ?: "")
                            .map({ account ->
                                if (account.name != null &&
                                        account.username != null &&
                                        account.id != null) {
                                    dataManager.setAccountId(account.id)
                                    dataManager.setName(account.name)
                                    dataManager.setUserName(account.username)
                                }
                                account
                            })
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({ account ->
                                if (!isViewAttached())
                                    return@subscribe
                                if (account != null) {
                                    mvpView?.showAccountDetails(account)
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

    override fun getFavourites() {
        if (dataManager.getIsUserLoggedIn()) {
            compositeDisposable.add(
                    dataManager.getFavourites(
                            dataManager.getAccountId() ?: 0,
                            Config.API_KEY,
                            dataManager.getSessionIdFromPreference() ?: "",
                            page.toString())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({ results ->
                                if (!isViewAttached())
                                    return@subscribe
                                //mvpView?.hideLoading()
                                if (results.results != null) {
                                    mvpView?.showFavourites(results.results!!)
                                    ++page
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
}