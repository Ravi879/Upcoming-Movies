package com.ravi.movies.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ravi.movies.data.repository.MovieRepository
import com.ravi.movies.domain.model.Movie
import com.ravi.movies.factory.base.BaseViewModel
import com.ravi.movies.util.rx.SchedulersFacade
import com.ravi.movies.viewmodel.contracts.MovieListContract
import javax.inject.Inject

class MainListVM
@Inject
constructor(var movieRepository: MovieRepository) : BaseViewModel() {

    lateinit var contract: MovieListContract

    var movieList = MutableLiveData<ArrayList<Movie>>()

    fun loadMovies() {
        addToDisposable(movieRepository.loadMoviesByUpcoming()
            .subscribeOn(SchedulersFacade.io())
            .observeOn(SchedulersFacade.ui())
            .doOnSubscribe { onLoading() }
            .subscribe({ list ->
                movieList.value = list
                onSuccess(null, true)
            }, { throwable ->
                onError(throwable, "Error occurred.", true)
            })
        )

    }


}
