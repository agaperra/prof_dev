package com.agaperra.professionaldevelopment

import android.app.Application
import android.os.StrictMode
import com.agaperra.professionaldevelopment.di.component.DaggerMainComponent
import com.agaperra.professionaldevelopment.di.component.MainComponent
import com.agaperra.professionaldevelopment.rx.SchedulerProvider
import android.os.StrictMode.VmPolicy


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

