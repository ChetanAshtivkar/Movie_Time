package com.chetan.movietime.data.pagination

/**
 * Created by Chetan on 2020-03-05.
 */
data class NetworkStatus(
    val status: Status,
    val error: String? = null
)

enum class Status {
    LOADING,
    LOADED,
    FAILED
}