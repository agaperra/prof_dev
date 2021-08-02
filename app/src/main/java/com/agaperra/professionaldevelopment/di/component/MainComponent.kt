package com.agaperra.professionaldevelopment.di.component

import android.content.Context
import com.agaperra.professionaldevelopment.MainApplication
import com.agaperra.professionaldevelopment.di.module.ApiModule
import com.agaperra.professionaldevelopment.di.module.DataModule
import com.agaperra.professionaldevelopment.di.module.viewmodel.ViewModelModule
import com.agaperra.professionaldevelopment.rx.ISchedulerProvider
import com.agaperra.professionaldevelopment.ui.activity.MainActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ViewModelModule::class,
        ApiModule::class,
        DataModule::class]
)
interface MainComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withSchedulers(schedulers: ISchedulerProvider): Builder

        fun build(): MainComponent
    }

    fun inject(application: MainApplication)

    fun inject(activity: MainActivity)

}