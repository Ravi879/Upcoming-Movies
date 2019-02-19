package com.ravi.movies.viewmodel

import com.ravi.movies.data.repository.MovieRepository
import com.ravi.movies.factory.base.BaseViewModel
import com.ravi.movies.util.rx.SchedulersFacade
import com.ravi.movies.viewmodel.contracts.MovieDetailContract
import javax.inject.Inject

class DetailsVM
@Inject
constructor(var movieRepository: MovieRepository) : BaseViewModel() {

    lateinit var contract: MovieDetailContract

    fun loadMovieDetails(movId: Int) {
        addToDisposable(movieRepository.loadMovieDetails(movId)
            .subscribeOn(SchedulersFacade.io())
            .observeOn(SchedulersFacade.ui())
            .doOnSubscribe { onLoading() }
            .subscribe({ list ->
                contract.onDetailsLoaded(list)
                onSuccess(null, true)
            }, { throwable ->
                onError(throwable, "Error occurred.", true)
            })
        )
    }


}
