package com.agaperra.professionaldevelopment.lesson_2.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.agaperra.professionaldevelopment.lesson_2.model.viewmodel.MainViewModel
import com.agaperra.professionaldevelopment.lesson_2.model.viewmodel.ViewModelFactory
import com.agaperra.professionaldevelopment.lesson_2.model.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [InteractorModule::class])
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    protected abstract fun mainViewModel(mainViewModel: MainViewModel): ViewModel
}