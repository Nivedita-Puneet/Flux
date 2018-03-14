package com.vicky7230.flux.ui.genres

import com.vicky7230.flux.data.Config
import com.vicky7230.flux.data.DataManager
import com.vicky7230.flux.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by vicky on 28/2/18.
 */
class GenresPresenter<V : GenresMvpView> @Inject constructor(
    private val dataManager: DataManager,
    private val compositeDisposable: CompositeDisposable
) : BasePresenter<V>(dataManager, compositeDisposable), GenresMvpPresenter<V> {

    override fun getGenres() {
        mvpView?.showLoading()
        compositeDisposable.add(
            dataManager.getGenres(Config.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ genres ->
                    if (!isViewAttached())
                        return@subscribe
                    mvpView?.hideLoading()
                    if (genres != null)
                        mvpView?.showGenres(genres.genres)
                }, { throwable ->
                    if (!isViewAttached())
                        return@subscribe
                    mvpView?.hideLoading()
                    mvpView?.showMessage(throwable.message!!)
                    Timber.e(throwable.message)
                })
        )
    }

    override fun saveGenres(genreIds: String) {
        mvpView?.showLoading()
        compositeDisposable.add(
            dataManager.setUserGenres(genreIds)
                .map({ boolean ->
                    dataManager.setGenresSelected()
                    boolean
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ boolean ->
                    if (!isViewAttached())
                        return@subscribe
                    mvpView?.hideLoading()
                    if (boolean != null)
                        mvpView?.goToHome()
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