package com.agaperra.repository.repository


import com.agaperra.repository.database.entity.Meaning
import com.agaperra.repository.datamodel.DictionaryResponse

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
        com.agaperra.repository.database.entity.Word(word, ts, tr)
}