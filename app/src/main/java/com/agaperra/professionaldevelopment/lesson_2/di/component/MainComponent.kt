package com.agaperra.professionaldevelopment.lesson_2.di.component

import android.app.Application
import com.agaperra.professionaldevelopment.lesson_2.application.MainApplication
import com.agaperra.professionaldevelopment.lesson_2.di.module.InteractorModule
import com.agaperra.professionaldevelopment.lesson_2.di.module.RepositoryModule
import com.agaperra.professionaldevelopment.lesson_2.di.module.UIModule
import com.agaperra.professionaldevelopment.lesson_2.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        InteractorModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        UIModule::class,
        AndroidInjectionModule::class]
)
@Singleton
interface MainComponent : AndroidInjector<MainApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): MainComponent
    }
}