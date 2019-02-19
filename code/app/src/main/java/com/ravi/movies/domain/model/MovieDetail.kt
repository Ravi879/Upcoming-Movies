package com.ravi.movies.domain.model


data class MovieDetail(
    var title: String,
    var overview: String,
    var popularity: Float,
    var images: ArrayList<String> = arrayListOf()
)