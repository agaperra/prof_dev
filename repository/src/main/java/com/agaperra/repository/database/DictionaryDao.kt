package com.agaperra.repository.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.agaperra.repository.database.entity.Meaning
import com.agaperra.repository.database.entity.Word

@Dao
interface DictionaryDao {
    @Insert
    suspend fun insertWord(word: Word)

    @Insert
    suspend fun insertMeanings(meanings: List<Meaning>)

    @Transaction
    @Query(value = "SELECT * FROM words WHERE word = :word")
    suspend fun getWordWithMeanings(word: String): Word_Meaning

    @Query(value = "SELECT * FROM words WHERE word = :word")
    suspend fun getWords(word: String): Word

    @Query(value = "SELECT * FROM meanings WHERE parent_word = :word")
    suspend fun getMeanings(word: String): Meaning
}