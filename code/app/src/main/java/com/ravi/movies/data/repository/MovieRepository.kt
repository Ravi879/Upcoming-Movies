package com.ravi.movies.data.repository

import com.ravi.movies.data.remote.api.MovieApiService
import com.ravi.movies.data.remote.model.MovieDetailResponse
import com.ravi.movies.domain.model.Movie
import com.ravi.movies.domain.model.MovieDetail
import com.ravi.movies.util.MovieUtil
import io.reactivex.Observable
import javax.inject.Inject

class MovieRepository
@Inject constructor(val movieApiService: MovieApiService) {

    fun loadMoviesByUpcoming(): Observable<ArrayList<Movie>> {
        val movieList: ArrayList<Movie> = arrayListOf()
        return movieApiService.fetchMovieByUpcoming()
            .map { apiResponse ->

                apiResponse.results.forEach { result ->
                    val movie = Movie(
                        result.id,
                        result.title,
                        result.posterPath,
                        result.releaseDate,
                        result.adult
                    )
                    movieList.add(movie)

                }
                movieList
            }

    }


    fun loadMovieDetails(movId: Int): Observable<MovieDetail> = movieApiService.fetchMovieDetails(movId)
        .map { apiResponse ->
            MovieDetail(
                apiResponse.title!!,
                apiResponse.overview!!,
                apiResponse.voteAverage!!.toFloat(),
                getMovieImages(apiResponse)
            )
        }

    private fun getMovieImages(response: MovieDetailResponse): ArrayList<String> {
        val movieImages = arrayListOf<String>()

        movieImages.add(MovieUtil.getTMDBImageURL(response.backdropPath))

        response.posterPath?.let {
            movieImages.add(MovieUtil.getTMDBImageURL(it))
        }

        response.belongsToCollection?.let { collection ->
            collection.posterPath?.let { imagePath ->
                movieImages.add(MovieUtil.getTMDBImageURL(imagePath))
            }
            collection.backdropPath?.let { imagePath ->
                movieImages.add(MovieUtil.getTMDBImageURL(imagePath))
            }

        }

        return movieImages
    }


}