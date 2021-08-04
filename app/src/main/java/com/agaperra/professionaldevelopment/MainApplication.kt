package com.agaperra.professionaldevelopment

import android.app.Application
import com.agaperra.professionaldevelopment.di.component.DaggerMainComponent
import com.agaperra.professionaldevelopment.di.component.MainComponent
import com.agaperra.professionaldevelopment.rx.SchedulerProvider


class MainApplication : Application() {

    companion object {
        lateinit var component: MainComponent
    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerMainComponent
            .builder()
            .withContext(applicationContext)
            .withSchedulers(SchedulerProvider)
            .build()

    }
}

