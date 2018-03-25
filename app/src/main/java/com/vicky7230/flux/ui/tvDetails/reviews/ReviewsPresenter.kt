package com.vicky7230.flux.ui.tvDetails.reviews

import com.vicky7230.flux.data.DataManager
import com.vicky7230.flux.ui.base.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by vicky on 25/3/18.
 */
class ReviewsPresenter<V : ReviewsMvpView> @Inject constructor(
        private val dataManager: DataManager,
        private val compositeDisposable: CompositeDisposable
) : BasePresenter<V>(dataManager, compositeDisposable), ReviewsMvpPresenter<V>