package com.amadoutirera.domain.interactor

import com.amadoutirera.domain.executor.PostExecutionThread
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<T,in params>constructor(private val postExecutionThread: PostExecutionThread){
    private val disposables = CompositeDisposable()



    protected abstract fun buildUseCaseSingle(params: params? = null): Observable<T>



    open fun execute(observer: DisposableObserver<T>, params: params? = null){
        val single = this.buildUseCaseSingle(params)
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)
        addDisposable(single.subscribeWith(observer))
    }


    private fun addDisposable(disposable: Disposable){
        disposables.add(disposable)
    }


    fun disposse(){
        if(!disposables.isDisposed()) disposables.dispose()
    }



}