package com.amadoutirera.domain.interactor

import com.amadoutirera.domain.executor.PostExecutionThread
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

abstract class ObservableUseCase<T,in params>constructor(private val postExecutionThread: PostExecutionThread){
    val disposables = CompositeDisposable()




    /*----------        ------------*/
    protected abstract fun buildUseCaseObservable(params: params? = null): Observable<T>



    /*----------        ------------*/
    open fun execute(observer: DisposableObserver<T>, params: params? = null){
        val observable = this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)
        addDisposable(observable.subscribeWith(observer))
    }

    private fun addDisposable(disposable: Disposable){
        disposables.add(disposable)
    }

}