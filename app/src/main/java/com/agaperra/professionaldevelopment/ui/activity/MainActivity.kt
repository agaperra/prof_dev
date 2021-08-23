package com.agaperra.professionaldevelopment.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.agaperra.core.BaseActivity
import com.agaperra.core.DictionaryInteractor
import com.agaperra.professionaldevelopment.R
import com.agaperra.professionaldevelopment.databinding.ActivityMainBinding
import com.agaperra.professionaldevelopment.koin.injectDependencies
import com.agaperra.professionaldevelopment.ui.adapter.MainAdapter
import com.agaperra.repository.state.AppState
import com.agaperra.utils.Constants
import com.agaperra.utils.Extensions.hide
import com.agaperra.utils.Extensions.show
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.android.scope.createScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope


class MainActivity : BaseActivity<AppState, MainInteractor>(), AndroidScopeComponent {

    private lateinit var binding: ActivityMainBinding
    private lateinit var meaningAdapter: MainAdapter
    override val scope: Scope by lazy { createScope(this) }
    private val mainViewModel: MainViewModel by viewModel()

    private lateinit var splitInstallManager: SplitInstallManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        val mLink = binding.yandex
        mLink.movementMethod = LinkMovementMethod.getInstance()
        Picasso.with(applicationContext)
            .load("https://octavian48.ru/upload/iblock/df4/df4f7ad6adc88e74fadd6c26c3fe2ce1.png")
            .placeholder(R.drawable.ic_baseline_image_not_supported_24).fit().centerInside()
            .into(binding.ivYandex)
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

        binding.rvMeanings.apply {
            layoutManager = LinearLayoutManager(context)
            meaningAdapter = MainAdapter()
            adapter = meaningAdapter
        }
    }

    private fun setupListeners() {
        binding.tilSearchLayout.setEndIconOnClickListener {
            mainViewModel.getData(binding.tieSearchView.text.toString())
        }
        binding.ivYandex.setOnClickListener {

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

        binding.tvWord.text = dataModel.data.word.word
        binding.tvTranscription.text = dataModel.data.word.ts
        binding.tvTranslation.text = dataModel.data.word.translate

        if (dataModel.data.meanings.isNotEmpty()) {
            meaningAdapter.updateList(dataModel.data.meanings)
            hideLoading()
        } else {
            binding.progressIndicator.hide()
            binding.groupResult.show()
            binding.meanings.hide()
            binding.rvMeanings.hide()
        }

    }


    private fun showError() {
        binding.progressIndicator.hide()
        binding.tvErrorMessage.show()
    }

    private fun showLoading() {
        (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(currentFocus?.windowToken, 0)

        binding.tvErrorMessage.hide()
        binding.groupResult.hide()
        binding.progressIndicator.show()
    }

    private fun hideLoading() {
        binding.progressIndicator.hide()
        binding.groupResult.show()
    }

}