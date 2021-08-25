package com.agaperra.professionaldevelopment.ui.activity

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.Group
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.agaperra.core.BaseActivity
import com.agaperra.professionaldevelopment.koin.injectDependencies
import com.agaperra.professionaldevelopment.ui.adapter.MainAdapter
import com.agaperra.repository.state.AppState
import com.agaperra.utils.Constants
import com.agaperra.utils.Extensions.hide
import com.agaperra.utils.Extensions.show
import com.agaperra.utils.Extensions.viewById
import com.agaperra.utils.OnlineLiveData
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import org.koin.android.scope.AndroidScopeComponent
import org.koin.android.scope.createScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

import androidx.core.graphics.drawable.DrawableCompat
import com.agaperra.professionaldevelopment.R


class MainActivity : BaseActivity<AppState, MainInteractor>(), AndroidScopeComponent {

    override val layoutRes = R.layout.activity_main
    private lateinit var meaningAdapter: MainAdapter
    override val scope: Scope by lazy { createScope(this) }
    private val mainViewModel: MainViewModel by viewModel()

    private lateinit var splitInstallManager: SplitInstallManager

    private val progressIndicator by viewById<CircularProgressIndicator>(R.id.progressIndicator)
    private val groupResult by viewById<Group>(R.id.groupResult)
    private val meanings by viewById<MaterialTextView>(R.id.meanings)
    private val rvMeanings by viewById<RecyclerView>(R.id.rvMeanings)
    private val tvErrorMessage by viewById<TextView>(R.id.tvErrorMessage)
    private val tvErrorOnline by viewById<TextView>(R.id.tvErrorOnline)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        val mLink by viewById<MaterialTextView>(R.id.yandex)
        val iView by viewById<ImageView>(R.id.ivYandex)
        mLink.movementMethod = LinkMovementMethod.getInstance()
        Picasso.with(applicationContext)
            .load("https://en.itmo.ru/module/isu_image_par.php?PARTNERS_ID=1088")
            .placeholder(R.drawable.ic_baseline_image_not_supported_24).fit().centerInside()
            .into(iView, object : Callback {
                override fun onSuccess() {
                    when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                        Configuration.UI_MODE_NIGHT_YES -> {
                            DrawableCompat.setTint(
                                iView.drawable,
                                applicationContext.resources.getColor(R.color.white)
                            )
                        } // Установлена тёмная тема
                    }
                }

                override fun onError() {
                    val constraintLayout by viewById<NestedScrollView>(R.id.constraintLayout)
                    Snackbar.make(
                        constraintLayout,
                        resources.getString(R.string.is_not_online),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            })

        initialize()
        setupListeners()
    }

    override fun renderData(dataModel: AppState) {
        when (dataModel) {
            is AppState.Error -> showError()
            is AppState.Loading -> showLoading()
            is AppState.Success -> setResult(dataModel)
        }
    }


    private fun initialize() {
        injectDependencies()
        mainViewModel.subscribe().observe(this@MainActivity, ::renderData)
        val recycler by viewById<RecyclerView>(R.id.rvMeanings)
        recycler.apply {
            layoutManager = LinearLayoutManager(context)
            meaningAdapter = MainAdapter()
            adapter = meaningAdapter
        }

        OnlineLiveData(
            application = this@MainActivity.application
        ).observe(this) { isAvailable ->
            when (isAvailable) {
                true -> connectionSnackBar.dismiss()
                false -> connectionSnackBar.show()
            }
        }
    }

    private fun setupListeners() {
        val search by viewById<TextInputLayout>(R.id.tilSearchLayout)
        val searchText by viewById<TextInputEditText>(R.id.tieSearchView)
        search.setEndIconOnClickListener {
            mainViewModel.getData(searchText.text.toString())
        }
        val iView by viewById<ImageView>(R.id.ivYandex)
        iView.setOnClickListener {

            splitInstallManager = SplitInstallManagerFactory.create(applicationContext)

            val request =
                SplitInstallRequest
                    .newBuilder()
                    .addModule(Constants.YANDEX_ACTIVITY_FEATURE_NAME)
                    .build()

            splitInstallManager
                .startInstall(request)
                .addOnSuccessListener {
                    val intent = Intent().setClassName(packageName, Constants.YANDEX_ACTIVITY_PATH)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Toast.makeText(
                        applicationContext,
                        "Couldn't download feature: " + it.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
        }

    }

    private fun setResult(dataModel: AppState.Success) {

        val tvWord by viewById<TextView>(R.id.tvWord)
        tvWord.text = dataModel.data.word.word
        val tvTranscription by viewById<TextView>(R.id.tvTranscription)
        tvTranscription.text = dataModel.data.word.ts
        val tvTranslation by viewById<TextView>(R.id.tvTranslation)
        tvTranslation.text = dataModel.data.word.translate

        if (dataModel.data.meanings.isNotEmpty()) {
            meaningAdapter.updateList(dataModel.data.meanings)
            hideLoading()
        } else {
            progressIndicator.hide()
            groupResult.show()
            meanings.hide()
            rvMeanings.hide()
        }

    }


    private fun showError() {
        progressIndicator.hide()
        tvErrorMessage.show()
    }

    private fun showLoading() {
        (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(currentFocus?.windowToken, 0)

        tvErrorMessage.hide()
        tvErrorOnline.hide()
        groupResult.hide()
        progressIndicator.show()
    }

    private fun hideLoading() {
        progressIndicator.hide()
        groupResult.show()
    }

    private val connectionSnackBar by lazy {
        val constraintLayout by viewById<NestedScrollView>(R.id.constraintLayout)
        Snackbar.make(
            constraintLayout,
            resources.getString(R.string.is_not_online),
            Snackbar.LENGTH_INDEFINITE
        )
    }


}