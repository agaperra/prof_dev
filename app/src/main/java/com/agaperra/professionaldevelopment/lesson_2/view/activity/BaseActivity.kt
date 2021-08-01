package com.agaperra.professionaldevelopment.lesson_2.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.agaperra.professionaldevelopment.R
import com.agaperra.professionaldevelopment.lesson_2.AppState
import com.agaperra.professionaldevelopment.lesson_2.utils.AlertDialogFragment
import com.agaperra.professionaldevelopment.lesson_2.utils.network.isOnline
import com.agaperra.professionaldevelopment.lesson_2.model.viewmodel.BaseViewModel
import com.agaperra.professionaldevelopment.lesson_2.model.viewmodel.interactor.DictionaryInteractor

abstract class BaseActivity<T : AppState, I : DictionaryInteractor<T>> : AppCompatActivity() {

    abstract val model: BaseViewModel<T>

    protected var isNetworkAvailable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        isNetworkAvailable = isOnline(applicationContext)
    }

    override fun onResume() {
        super.onResume()
        isNetworkAvailable = isOnline(applicationContext)
        if (!isNetworkAvailable && isDialogNull()) {
            showNoInternetConnectionDialog()
        }
    }

    protected fun showNoInternetConnectionDialog() {
        showAlertDialog(
            getString(R.string.dialog_title_device_is_offline),
            getString(R.string.dialog_message_device_is_offline)
        )
    }

    protected fun showAlertDialog(title: String?, message: String?) {
        AlertDialogFragment.newInstance(title, message).show(supportFragmentManager, DIALOG_FRAGMENT_TAG)
    }

    private fun isDialogNull(): Boolean {
        return supportFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) == null
    }

    abstract fun renderData(dataModel: T)

    companion object {
        private const val DIALOG_FRAGMENT_TAG = "74a54328-5d62-46bf-ab6b-cbf5d8c79522"
    }
}
