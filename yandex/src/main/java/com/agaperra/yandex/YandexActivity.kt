package com.agaperra.yandex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.yandex_layout.*

class YandexActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.yandex_layout)
        injectDependencies()
        yandexTV.text = resources.getString(R.string.yandexnv)
    }

}