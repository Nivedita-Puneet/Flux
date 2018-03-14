package com.vicky7230.flux.ui.splash

import com.vicky7230.flux.data.Config
import com.vicky7230.flux.data.DataManager
import com.vicky7230.flux.data.db.room.model.ConfigurationDbModel
import com.vicky7230.flux.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by vicky on 1/3/18.
 */
class SplashPresenter<V : SplashMvpView> @Inject constructor(
    private val dataManager: DataManager,
    private val compositeDisposable: CompositeDisposable
) : BasePresenter<V>(dataManager, compositeDisposable), SplashMvpPresenter<V> {

    override fun getConfigurations() {
        //mvpView?.showLoading()
        compositeDisposable.add(
            dataManager.getConfigurations(Config.API_KEY)
                .map({ configuration ->

                    dataManager.deleteConfigurations()

                    val configurationList = mutableListOf<ConfigurationDbModel>()
                    configuration.images?.backdropSizes!!.mapTo(configurationList) {
                        ConfigurationDbModel(
                            null,
                            "backdrop_sizes",
                            it
                        )
                    }
                    configuration.images?.logoSizes!!.mapTo(configurationList) {
                        ConfigurationDbModel(
                            null,
                            "logo_sizes",
                            it
                        )
                    }
                    configuration.images?.posterSizes!!.mapTo(configurationList) {
                        ConfigurationDbModel(
                            null,
                            "poster_sizes",
                            it
                        )
                    }

                    configuration.images?.profileSizes!!.mapTo(configurationList) {
                        ConfigurationDbModel(
                            null,
                            "profile_sizes",
                            it
                        )
                    }

                    configuration.images?.stillSizes!!.mapTo(configurationList) {
                        ConfigurationDbModel(
                            null,
                            "still_sizes",
                            it
                        )
                    }
                    configuration.changeKeys!!.mapTo(configurationList) {
                        ConfigurationDbModel(
                            null,
                            "change_keys",
                            it
                        )
                    }

                    dataManager.insertConfigurations(configurationList)
                    dataManager.setBaseImageUrl(configuration.images?.secureBaseUrl)
                    configuration
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ configuration ->
                    if (!isViewAttached())
                        return@subscribe
                    //mvpView?.hideLoading()
                    if (configuration != null) {
                        //mvpView?.showMessage("Success")
                        decideNextActivity()
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

    private fun decideNextActivity() {
        //Whether the user has selected the genres
        if (dataManager.getGenresSelected()) {
           mvpView?.goToHomeScreen()
        } else {
            mvpView?.gotToGenreScreen()
        }
    }

}