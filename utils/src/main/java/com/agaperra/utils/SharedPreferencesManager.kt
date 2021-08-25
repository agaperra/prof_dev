package com.agaperra.utils

import android.app.Activity
import android.content.SharedPreferences

class SharedPreferencesManager(activity: Activity) {

    private val sPreferences: SharedPreferences = activity.getPreferences(Activity.MODE_PRIVATE)

    var sEditor: SharedPreferences.Editor? = null
    private val editor: SharedPreferences.Editor
        get() = sPreferences.edit()

    fun retrieveInt(tag: String?, defValue: Int): Int {
        return sPreferences.getInt(tag, defValue)
    }

    fun storeInt(tag: String?, defValue: Int) {
        sEditor = editor
        sEditor?.putInt(tag, defValue)
        sEditor?.commit()
    }

}