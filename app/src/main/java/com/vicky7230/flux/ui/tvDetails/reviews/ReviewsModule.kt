package com.vicky7230.flux.ui.tvDetails.reviews

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.vicky7230.flux.di.ApplicationContext
import dagger.Module
import dagger.Provides

/**
 * Created by vicky on 25/3/18.
 */

@Module
class ReviewsModule {

    @Provides
    fun provideReviewsMvpPresenter(presenter: ReviewsPresenter<ReviewsMvpView>): ReviewsMvpPresenter<ReviewsMvpView> {
        return presenter
    }

    @Provides
    fun provideLinearLayoutManager(@ApplicationContext context: Context): LinearLayoutManager {
        return LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    @Provides
    fun priovideReviesListAdapter(): ReviewsListAdapter {
        return ReviewsListAdapter(arrayListOf())
    }
}
