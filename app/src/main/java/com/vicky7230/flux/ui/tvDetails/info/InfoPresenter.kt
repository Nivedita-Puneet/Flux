package com.vicky7230.flux.ui.tvDetails.info

import com.vicky7230.flux.data.DataManager
import com.vicky7230.flux.ui.base.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by vicky on 24/3/18.
 */
class InfoPresenter<V : InfoMvpView> @Inject constructor(
        private val dataManager: DataManager,
        private val compositeDisposable: CompositeDisposable
) : BasePresenter<V>(dataManager, compositeDisposable), InfoMvpPresenter<V>