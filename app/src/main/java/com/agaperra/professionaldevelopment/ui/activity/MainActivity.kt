package com.agaperra.professionaldevelopment.ui.activity

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.agaperra.professionaldevelopment.databinding.ActivityMainBinding
import com.agaperra.professionaldevelopment.utils.Extensions.hide
import com.agaperra.professionaldevelopment.utils.Extensions.show
import com.agaperra.professionaldevelopment.MainApplication
import com.agaperra.professionaldevelopment.R
import com.agaperra.professionaldevelopment.data.state.AppState
import com.agaperra.professionaldevelopment.ui.adapter.MainAdapter
import com.agaperra.professionaldevelopment.ui.base.BaseActivity
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
        model.subscribe().observe(this, ::renderData)

        binding.rvMeanings.apply {
            layoutManager = LinearLayoutManager(context)
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
        println(dataModel)
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