package com.agaperra.yandex

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.agaperra.utils.Extensions.viewById
import org.w3c.dom.Text

class YandexActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.yandex_layout)

        val yandexTV by viewById<TextView>(R.id.yandexTV)
        val yandexInfo by viewById<TextView>(R.id.yandexInfo)

        yandexTV.text = resources.getString(R.string.yandexnv)
        yandexInfo.text = resources.getString(R.string.yandexinfo)
    }

}