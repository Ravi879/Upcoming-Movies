package com.ravi.movies

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.lifecycle.MutableLiveData
import com.ravi.movies.di.base.DaggerAppComponent
import com.ravi.movies.domain.reciever.NetworkReceiver
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class AppEntry : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    companion object {
        var isNetworkAvailable: MutableLiveData<Boolean> = MutableLiveData()
        lateinit var getAppContext: Context
    }

    private var mNetworkReceiver: NetworkReceiver? = null

    override fun onCreate() {
        super.onCreate()

        getAppContext = this

        DaggerAppComponent
            .builder()
            .application(this)
            .build()
            .inject(this)

        registerNetworkRegister()
    }


    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    private fun registerNetworkRegister() {
        mNetworkReceiver = NetworkReceiver()
        val networkFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(mNetworkReceiver, networkFilter)
    }


}
