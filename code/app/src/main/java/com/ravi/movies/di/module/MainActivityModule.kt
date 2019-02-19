package com.ravi.movies.di.module

import dagger.Module
import dagger.Provides
import com.ravi.movies.ui.activity.MovieListActivity
import com.ravi.movies.viewmodel.contracts.MovieListContract


@Module
class MainActivityModule {

    @Provides
    fun provideMainView(movieListActivity: MovieListActivity): MovieListContract {
        return movieListActivity
    }



}
