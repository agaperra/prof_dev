package com.agaperra.utils

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

object Extensions {

    fun View.show() {
        visibility = View.VISIBLE
    }

    fun View.hide() {
        visibility = View.INVISIBLE
    }


    fun <T : View> Activity.viewById(@IdRes viewId: Int): ViewByIdDelegate<T> {
        return ViewByIdDelegate({ window.decorView.findViewById(android.R.id.content) }, viewId)
    }

    fun <T : View> Fragment.viewById(@IdRes viewId: Int): ViewByIdDelegate<T> {
        return ViewByIdDelegate({ view }, viewId)
    }



}