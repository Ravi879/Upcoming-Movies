package com.ravi.movies.data.remote.model

import com.google.gson.annotations.SerializedName


data class MovieListResponse(
    @SerializedName("results")
    var results: List<Result>,
    @SerializedName("page")
    var page: Int,
    @SerializedName("total_results")
    var totalResults: Int,
    @SerializedName("dates")
    var dates: Dates,
    @SerializedName("total_pages")
    var totalPages: Int
)