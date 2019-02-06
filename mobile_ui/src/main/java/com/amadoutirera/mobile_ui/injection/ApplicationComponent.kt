package com.amadoutirera.mobile_ui.injection

import android.app.Application
import android.app.Presentation
import com.amadoutirera.mobile_ui.GithubTrendingApplication
import com.amadoutirera.mobile_ui.injection.module.ApplicationModule
import com.amadoutirera.mobile_ui.injection.module.PresentationModule
import com.amadoutirera.mobile_ui.injection.module.UiModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class,
    ApplicationModule::class,
    UiModule::class,
    PresentationModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }


    fun inject(app: GithubTrendingApplication)


}