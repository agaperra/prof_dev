package com.agaperra.professionaldevelopment.ui.activity

import android.media.MediaPlayer
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import com.agaperra.professionaldevelopment.databinding.ActivityMainBinding
import com.agaperra.professionaldevelopment.lesson_2.Extensions.disable
import com.agaperra.professionaldevelopment.lesson_2.Extensions.enable
import com.agaperra.professionaldevelopment.lesson_2.Extensions.hide
import com.agaperra.professionaldevelopment.lesson_2.Extensions.show
import com.agaperra.professionaldevelopment.MainApplication
import com.agaperra.professionaldevelopment.data.state.AppState
import com.agaperra.professionaldevelopment.ui.adapter.MainAdapter
import com.agaperra.professionaldevelopment.ui.base.BaseActivity
import com.google.android.material.tabs.TabLayout
import javax.inject.Inject


class MainActivity : BaseActivity<AppState, MainInteractor>() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: ActivityMainBinding
    private lateinit var meaningAdapter: MainAdapter


    override val model: MainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        MainApplication.component.inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

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
        model.subscribe().observe(this, ::renderData)

        binding.rvMeanings.apply {
            meaningAdapter = MainAdapter()
            adapter = meaningAdapter
        }
    }

    private fun setupListeners() {
        binding.tilSearchLayout.setEndIconOnClickListener {
            model.getData(binding.tieSearchView.text.toString())
        }

    }

    private fun setResult(dataModel: AppState.Success) {
        binding.tvWord.text = dataModel.data.word.word
        meaningAdapter.updateList(dataModel.data.meanings)
        hideLoading()

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