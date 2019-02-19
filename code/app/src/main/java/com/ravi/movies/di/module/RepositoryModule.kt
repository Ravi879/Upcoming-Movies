package com.ravi.movies.di.module

import dagger.Module
import dagger.Provides
import com.ravi.movies.data.remote.api.MovieApiService
import com.ravi.movies.data.repository.MovieRepository

@Module(includes = [MovieApiModule::class])
class RepositoryModule {


    @Provides
    fun provideMovieApiService(movieApiService: MovieApiService): MovieRepository {
        return MovieRepository(movieApiService)
    }


}
