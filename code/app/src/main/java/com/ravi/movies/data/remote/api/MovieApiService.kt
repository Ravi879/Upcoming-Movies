package com.ravi.movies.data.remote.api

import com.ravi.movies.data.remote.model.MovieDetailResponse
import com.ravi.movies.data.remote.model.MovieListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {

    @GET("upcoming")
    fun fetchMovieByUpcoming(): Observable<MovieListResponse>

    @GET("{movId}")
    fun fetchMovieDetails(@Path("movId") movieId: Int): Observable<MovieDetailResponse>


}