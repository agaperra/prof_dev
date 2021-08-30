package com.agaperra.utils

import android.view.View

object Extensions {

    fun View.show() {
        visibility = View.VISIBLE
    }

    fun View.hide() {
        visibility = View.INVISIBLE
    }


}