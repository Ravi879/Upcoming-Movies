package com.ravi.movies.viewmodel.contracts

import com.ravi.movies.domain.model.MovieDetail


interface MovieDetailContract {
    fun onDetailsLoaded(movieDetail: MovieDetail)
}
