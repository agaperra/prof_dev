package com.agaperra.professionaldevelopment.data.repository

import com.agaperra.professionaldevelopment.data.database.entity.Meaning
import com.agaperra.professionaldevelopment.data.database.entity.Word
import com.agaperra.professionaldevelopment.data.network.model.DictionaryResponse

object Converter {

    fun convertToMeanings(response: DictionaryResponse): List<Meaning> {
        val meanings = mutableListOf<Meaning>()
            for (tr in response.def[0].tr) {
                for (mean in tr.mean) {
                    meanings.add(Meaning(response.def[0].text, mean.text))
                }
            }
        return meanings
    }

    fun convertToWord(word: String, ts: String, tr: String) =
        Word(word, ts, tr)
}