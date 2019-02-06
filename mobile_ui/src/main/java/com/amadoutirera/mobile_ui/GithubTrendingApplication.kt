package com.amadoutirera.mobile_ui

import android.app.Activity
import android.app.Application
import android.app.Fragment
import com.amadoutirera.mobile_ui.injection.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasFragmentInjector

import timber.log.Timber
import javax.inject.Inject

class GithubTrendingApplication: Application(), HasActivityInjector, HasFragmentInjector {
    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>



    override fun onCreate() {
        super.onCreate()
        setupTimber()


        DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }





    private fun setupTimber(){
        Timber.plant(Timber.DebugTree())
    }



    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }

    override fun fragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }


}