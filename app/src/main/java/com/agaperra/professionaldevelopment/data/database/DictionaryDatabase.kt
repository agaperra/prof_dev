package com.agaperra.professionaldevelopment.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.agaperra.professionaldevelopment.data.database.entity.Meaning
import com.agaperra.professionaldevelopment.data.database.entity.Word


@Database(entities = [Meaning::class, Word::class], version = 3, exportSchema = false)
abstract class DictionaryDatabase : RoomDatabase() {
    abstract fun dictionaryDao(): DictionaryDao
}