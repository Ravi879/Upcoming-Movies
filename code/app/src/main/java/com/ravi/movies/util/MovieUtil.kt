package com.ravi.movies.util


object MovieUtil {

    fun getTMDBImageURL(imageName: String) =
        Constants.TMDB_BASE_IMG_URL + imageName

    fun getPosterUrl(posterName: String): String {
        return Constants.TMDB_BASE_POSTER_IMG_URL + posterName
    }

}