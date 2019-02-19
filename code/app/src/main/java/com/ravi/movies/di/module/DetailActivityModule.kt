package com.ravi.movies.di.module

import dagger.Module
import dagger.Provides
import com.ravi.movies.ui.activity.MovieDetailActivity
import com.ravi.movies.viewmodel.contracts.MovieDetailContract

@Module
class DetailActivityModule {

    @Provides
    fun provideDetailView(movieDetailActivity: MovieDetailActivity): MovieDetailContract {
        return movieDetailActivity
    }


}
