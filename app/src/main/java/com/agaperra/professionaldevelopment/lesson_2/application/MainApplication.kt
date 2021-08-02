package com.agaperra.professionaldevelopment.lesson_2.application

import com.agaperra.professionaldevelopment.lesson_2.rx.SchedulerProvider
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MainApplication : DaggerApplication()  {


    override fun applicationInjector(): AndroidInjector<MainApplication> = DaggerMainComponent
        .builder()
        .withContext(applicationContext)
        .withSchedulers(SchedulerProvider)
        .build()

}
