package com.ravi.movies.di.base

import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.ravi.movies.di.module.DetailActivityModule
import com.ravi.movies.di.module.MainActivityModule
import com.ravi.movies.ui.activity.MovieDetailActivity
import com.ravi.movies.ui.activity.MovieListActivity

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MovieListActivity

    @ContributesAndroidInjector(modules = [DetailActivityModule::class])
    abstract fun bindDetailActivity(): MovieDetailActivity

}
