package com.agaperra.repository.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meanings")
data class Meaning(
    @ColumnInfo(name = "parent_word")
    var parentWord: String,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "text")
    var text: String
)