package com.ravi.movies.domain.contract

interface NetworkStateContract {

        fun onNetworkStateChange(isAvailable: Boolean)

}
