package com.ravi.movies.data.remote.model

import com.google.gson.annotations.SerializedName


data class Result(
    @SerializedName("vote_count")
    var voteCount: Int,
    @SerializedName("id")
    var id: Int,
    @SerializedName("video")
    var video: Boolean,
    @SerializedName("vote_average")
    var voteAverage: Double,
    @SerializedName("title")
    var title: String,
    @SerializedName("popularity")
    var popularity: Double,
    @SerializedName("poster_path")
    var posterPath: String,
    @SerializedName("original_language")
    var originalLanguage: String,
    @SerializedName("original_title")
    var originalTitle: String,
    @SerializedName("genre_ids")
    var genreIds: List<Int>,
    @SerializedName("backdrop_path")
    var backdropPath: String,
    @SerializedName("adult")
    var adult: Boolean,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("release_date")
    var releaseDate: String
)