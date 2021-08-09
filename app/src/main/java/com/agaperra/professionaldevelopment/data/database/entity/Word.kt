package com.agaperra.professionaldevelopment.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class Word(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "word")
    var word: String
)