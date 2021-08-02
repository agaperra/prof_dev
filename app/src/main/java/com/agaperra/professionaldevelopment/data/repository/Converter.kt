package com.agaperra.professionaldevelopment.data.repository

import com.agaperra.professionaldevelopment.data.database.entity.Meaning
import com.agaperra.professionaldevelopment.data.database.entity.Word
import com.agaperra.professionaldevelopment.data.network.model.DictionaryResponse
import com.agaperra.professionaldevelopment.data.network.model.Mean
import com.agaperra.professionaldevelopment.data.network.model.Tr

object Converter {

    fun convertToMeanings(response: DictionaryResponse): List<Meaning> {
        val meanings = mutableListOf<Meaning>()
        for (meaning in response.def[0].tr) {
            for (definition in meaning.mean) {
                meanings.add(Meaning(meaning.text, definition.text))
            }
        }
        return meanings
    }

    fun convertToWord(word: String) =
        Word(word)
}