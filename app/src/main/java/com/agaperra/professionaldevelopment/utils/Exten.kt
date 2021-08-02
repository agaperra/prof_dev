package com.agaperra.professionaldevelopment.lesson_2

import android.view.View

object Extensions {

    fun View.show() {
        visibility = View.VISIBLE
    }

    fun View.hide() {
        visibility = View.INVISIBLE
    }

    fun View.enable() {
        isEnabled = true
        alpha = 1f
    }

    fun View.disable() {
        isEnabled = false
        alpha = 0.5f
    }

}