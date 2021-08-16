package com.agaperra.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.agaperra.repository.database.entity.Meaning
import com.agaperra.repository.database.entity.Word


@Database(entities = [Meaning::class, Word::class], version = 4, exportSchema = false)
abstract class DictionaryDatabase : RoomDatabase() {
    abstract fun dictionaryDao(): DictionaryDao
}