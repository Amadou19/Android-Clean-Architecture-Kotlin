package com.amadoutirera.mobile_ui.injection.module

import com.amadoutirera.domain.executor.PostExecutionThread
import com.amadoutirera.mobile_ui.BrowseActivity
import com.amadoutirera.mobile_ui.UiThread
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class UiModule {

    @Binds
    abstract fun bindPostExecuteThread(uiThread: UiThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesBrowseActivity(): BrowseActivity

}