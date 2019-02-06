package com.amadoutirera.domain.interactor

import com.amadoutirera.domain.executor.PostExecutionThread
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import sun.tools.jar.CommandLine

abstract class CompletableUseCase<in params>constructor(private val postExecutionThread: PostExecutionThread){
    val disposables = CompositeDisposable()




    protected abstract fun buildUseCasCompletable(params: params? = null): Completable




    open fun execute(observer: DisposableCompletableObserver, params: params? = null){
        val completable = this.buildUseCasCompletable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)
        addDisposable(completable.subscribeWith(observer))
    }

    private fun addDisposable(disposable: Disposable){
        disposables.add(disposable)
    }

}