package com.example.githubsample.utils

import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.components.support.RxFragment
import io.reactivex.CompletableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class RxLifecycleOwnerV3(private val fragment: RxFragment?) : RxLifecycleOwner {
    override fun <T> bindToLifecycle(): LifecycleTransformer<T> {
        return fragment!!.bindToLifecycle()
    }

    override fun <T> moveToBackground(): SingleTransformer<T, T> {
        return SingleTransformer { upstream ->
            upstream.compose(fragment!!.bindToLifecycle<T>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        }
    }

    fun <T> moveCompletableToBackground(): CompletableTransformer {
        return CompletableTransformer { upstream ->
            upstream.compose(fragment!!.bindToLifecycle<T>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        }
    }

    override fun <T> moveObservableToBackground(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream.compose(fragment!!.bindToLifecycle<T>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        }
    }
}