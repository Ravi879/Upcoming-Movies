package com.ravi.movies.factory.base

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.ravi.movies.AppEntry
import com.ravi.movies.R
import com.ravi.movies.domain.contract.NetworkStateContract
import com.ravi.movies.util.loadingtracker.Response
import com.ravi.movies.util.loadingtracker.Status
import com.ravi.movies.util.toast

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(), NetworkStateContract {

    //the following variables must initialized in every activity
    lateinit var networkSnackBar: Snackbar
    lateinit var loadingIndicator: View

    private val tag = "BaseActivityKT"

    //As per the n/w state, snackbar is shows and hide.
    override fun onNetworkStateChange(isAvailable: Boolean) {
        when (isAvailable) {
            true -> networkSnackBar.dismiss()
            else -> networkSnackBar.show()
        }
    }

    //Snackbar that shows when, internet is not available
    fun getNetworkSnackBar(layout: View): Snackbar {
        val snackBar: Snackbar = Snackbar.make(
            layout,
            "No Connection :(",
            Snackbar.LENGTH_INDEFINITE
        ).setAction("Ok") {}

        snackBar.setActionTextColor(ContextCompat.getColor(AppEntry.getAppContext, R.color.snackbar_action))

        return snackBar
    }


    //this Observer is used for observe the live-data variable, that is present in  class-"AppEntry" , static variable-"isNetworkAvailable"
    fun getNetworkObserver() = Observer<Boolean> { isVisible: Boolean ->
        onNetworkStateChange(isVisible)
    }

    //this called in Activity , where it is used to observe the changing the state in variable named as "response"
    // which is present in  View-Model class.
    fun processResponse(response: Response) {
        when (response.status) {
            Status.LOADING -> renderLoadingState()
            Status.SUCCESS -> renderDataState(response.data)
            Status.ERROR -> renderErrorState(response.error!!, response.data!!)
        }
    }

    //show loading
    private fun renderLoadingState() {
        loadingIndicator.visibility = View.VISIBLE
        disableWindowTouch()
    }

    //loading may be successful, hide the loading view7
    private fun renderDataState(msg: String?) {
        loadingIndicator.visibility = View.GONE
        msg?.let { toast(it) }
        enableWindowTouch()
    }

    //loading error occurs, hide the loading view
    private fun renderErrorState(throwable: Throwable, errorMsg: String) {
        loadingIndicator.visibility = View.GONE
        toast(errorMsg)
        enableWindowTouch()

        throwable.printStackTrace()
        Log.v(tag, "Error $throwable")
    }


    //===============   Enable - Disabled window touch    ===============
    private fun disableWindowTouch() {
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    private fun enableWindowTouch() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

}