package com.agaperra.professionaldevelopment.ui.adapter

interface MeaningsPresenter : WordsListPresenter<MainViewHolder>

interface WordsListPresenter<V> {
    fun bindView(view: V, position: Int)
    fun getCount(): Int
}