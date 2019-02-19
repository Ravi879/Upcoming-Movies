package com.ravi.movies.di.base

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import com.ravi.movies.AppEntry
import com.ravi.movies.di.module.MovieApiModule
import com.ravi.movies.di.module.ViewModelModule

import javax.inject.Singleton

@Component(
    modules = [AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBuilder::class,
        ViewModelModule::class,
        MovieApiModule::class]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: AppEntry)
}
