package com.ravi.movies.domain.reciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.ravi.movies.AppEntry

class NetworkReceiver : BroadcastReceiver() {

    override fun onReceive(p0: Context?, intent: Intent?) {
        if (ConnectivityManager.CONNECTIVITY_ACTION == intent!!.action) {
            val isAvailable = !(intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false))
            AppEntry.isNetworkAvailable.value = isAvailable
        }
    }

}