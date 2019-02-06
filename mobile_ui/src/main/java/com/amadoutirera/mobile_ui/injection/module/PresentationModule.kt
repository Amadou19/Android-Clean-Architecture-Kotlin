package com.amadoutirera.mobile_ui.injection.module

import androidx.lifecycle.ViewModel
import com.amadoutirera.presentation.BrowsBookMarkProjectViewModel
import com.amadoutirera.presentation.BrowsProjectViewModel
import dagger.Binds
import dagger.MapKey
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass


abstract class PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(BrowsBookMarkProjectViewModel::class)
    abstract fun bindBrowseProjectViewmodel(viewModel: BrowsBookMarkProjectViewModel): ViewModel


}

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)