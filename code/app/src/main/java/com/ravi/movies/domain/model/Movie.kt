package com.ravi.movies.domain.model


data class Movie(

    var id: Int,
    var title: String,
    var posterUrl: String,
    var releaseDate: String,
    var isAdult: Boolean

)
