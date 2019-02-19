package com.ravi.movies.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ravi.movies.di.annotations.ViewModelKey
import com.ravi.movies.factory.ViewModelFactory
import com.ravi.movies.viewmodel.DetailsVM
import com.ravi.movies.viewmodel.MainListVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainListVM::class)
    abstract fun mainActivityModel(mainListVM: MainListVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsVM::class)
    abstract fun detailActivityModel(detailsVM: DetailsVM): ViewModel

}