package com.ravi.movies.di.base

import android.app.Application
import android.content.Context

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
open class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

}
