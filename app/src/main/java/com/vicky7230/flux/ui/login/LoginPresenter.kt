package com.vicky7230.flux.ui.login

import com.vicky7230.flux.data.Config
import com.vicky7230.flux.data.DataManager
import com.vicky7230.flux.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by vicky on 16/3/18.
 */
class LoginPresenter<V : LoginMvpView> @Inject constructor(
    private val dataManager: DataManager,
    private val compositeDisposable: CompositeDisposable
) : BasePresenter<V>(dataManager, compositeDisposable), LoginMvpPresenter<V> {

    override fun requestAuthenticationToken() {
        mvpView?.showLoading()
        compositeDisposable.add(
            dataManager.requestAuthenticationToken(
                Config.API_KEY
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ authenticationToken ->
                    if (!isViewAttached())
                        return@subscribe
                    mvpView?.hideLoading()
                    if (authenticationToken != null) {
                        if (authenticationToken.success == true) {
                            mvpView?.launchBrowserForLogin(authenticationToken.requestToken)
                        } else {
                            mvpView?.showError("Something went wrong.")
                        }
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

    override fun getSessionId(requestToken: String?) {
        //mvpView?.showLoading()
        compositeDisposable.add(
            dataManager.getSessionId(
                Config.API_KEY,
                requestToken ?: ""
            ).map({ session ->
                if (session.success == true) {
                    dataManager.setSessionIdIntoPreference(session.sessionId)
                    dataManager.setIsUserLoggedIn()
                }
                session
            })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ session ->
                    if (!isViewAttached())
                        return@subscribe
                    //mvpView?.hideLoading()
                    if (session != null) {
                        if (session.success == true) {
                            mvpView?.showMessage("Login Successful.")
                            mvpView?.finishLosinScreen()
                        } else {
                            mvpView?.showError("Something went wrong.")
                        }
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