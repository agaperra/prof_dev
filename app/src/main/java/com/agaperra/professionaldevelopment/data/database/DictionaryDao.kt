package com.agaperra.professionaldevelopment.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.agaperra.professionaldevelopment.data.database.entity.Meaning
import com.agaperra.professionaldevelopment.data.database.entity.Word
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface DictionaryDao {
    @Insert
    fun insertWord(word: Word) : Completable

    @Insert
    fun insertMeanings(meanings: List<Meaning>) : Completable

    @Transaction
    @Query(value = "SELECT * FROM words WHERE word = :word")
    fun getWordWithMeanings(word: String): Single<Word_Meaning>

    @Query(value = "SELECT * FROM words WHERE word = :word")
    fun getWords(word: String): Single<Word>

    @Query(value = "SELECT * FROM meanings WHERE parent_word = :word")
    fun getMeanings(word: String): Single<Meaning>
}