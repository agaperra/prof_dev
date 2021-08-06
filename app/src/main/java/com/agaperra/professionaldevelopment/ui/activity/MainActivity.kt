package com.agaperra.professionaldevelopment.ui.activity

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.agaperra.professionaldevelopment.data.state.AppState
import com.agaperra.professionaldevelopment.databinding.ActivityMainBinding
import com.agaperra.professionaldevelopment.ui.adapter.MainAdapter
import com.agaperra.professionaldevelopment.ui.base.BaseActivity
import com.agaperra.professionaldevelopment.utils.Extensions.hide
import com.agaperra.professionaldevelopment.utils.Extensions.show
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<AppState, MainInteractor>() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var meaningAdapter: MainAdapter

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        val mLink = binding.yandex
        mLink.movementMethod = LinkMovementMethod.getInstance()

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
        mainViewModel.subscribe().observe(this, ::renderData)

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

    }

    private fun setResult(dataModel: AppState.Success) {
        binding.tvWord.text = dataModel.data.word.word
        if(dataModel.data.meanings.isNotEmpty()) {
            meaningAdapter.updateList(dataModel.data.meanings)
            hideLoading()
        }
        else{
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