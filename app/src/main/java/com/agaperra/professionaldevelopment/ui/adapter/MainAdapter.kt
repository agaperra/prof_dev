package com.agaperra.professionaldevelopment.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.agaperra.professionaldevelopment.data.database.entity.Meaning
import com.agaperra.professionaldevelopment.databinding.ItemMeaningBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.MeaningViewHolderImpl>() {

    private val itemList = mutableListOf<Meaning>()

    inner class MeaningViewHolderImpl(private val binding: ItemMeaningBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindMeaning(meaning: Meaning) {
            binding.tvMeaning.text = meaning.text
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MeaningViewHolderImpl(
        ItemMeaningBinding.inflate(LayoutInflater.from(parent.context))
    )

    override fun onBindViewHolder(holder: MeaningViewHolderImpl, position: Int) =
        holder.bindMeaning(itemList[position])

    override fun getItemCount() = itemList.count()

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newItems: List<Meaning>) {
        itemList.clear()
        println(newItems)
        itemList.addAll(newItems)
        notifyDataSetChanged()
    }

}
