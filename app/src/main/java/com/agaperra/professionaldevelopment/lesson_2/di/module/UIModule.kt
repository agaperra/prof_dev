package com.agaperra.professionaldevelopment.lesson_2.di.module

import com.agaperra.professionaldevelopment.lesson_2.view.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UIModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}
