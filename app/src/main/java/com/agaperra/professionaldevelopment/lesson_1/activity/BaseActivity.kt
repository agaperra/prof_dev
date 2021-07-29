package com.agaperra.professionaldevelopment.lesson_1.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.agaperra.professionaldevelopment.lesson_1.AppState
import com.agaperra.professionaldevelopment.lesson_1.presenter.DictionaryPresenter
import com.agaperra.professionaldevelopment.lesson_1.view.DictionaryView

abstract class BaseActivity<T : AppState> : AppCompatActivity(), DictionaryView {

    protected lateinit var presenter: DictionaryPresenter<T, DictionaryView>

    protected abstract fun createPresenter(): DictionaryPresenter<T, DictionaryView>

    abstract override fun renderData(appState: AppState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }


    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }
}
