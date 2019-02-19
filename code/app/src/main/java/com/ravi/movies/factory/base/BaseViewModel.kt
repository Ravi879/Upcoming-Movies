package com.ravi.movies.factory.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ravi.movies.util.loadingtracker.Response
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    private val tag = "BaseVMKT"

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val response = MutableLiveData<Response>()

    protected fun addToDisposable(disposable: Disposable) {
        compositeDisposable.remove(disposable)
        compositeDisposable.add(disposable)
    }


    fun onLoading() {
        response.value = Response.loading()
    }

    fun onSuccess(msg: String?, isClearDisposable: Boolean) {
        response.value = Response.success(msg)
        if (isClearDisposable) clearDisposable()
    }

    fun onError(throwable: Throwable, msg: String, isClearDisposable: Boolean) {
        response.value = Response.error(throwable, msg)
        if (isClearDisposable) clearDisposable()
    }


    private fun clearDisposable() {
        compositeDisposable.clear()
    }


    //dispose the Observables for avoiding leaks and extra memory consumptions
    override fun onCleared() {
        super.onCleared()
        Log.v(tag, "onCleared " + compositeDisposable.size())
        if (!compositeDisposable.isDisposed)
            compositeDisposable.dispose()
    }

}