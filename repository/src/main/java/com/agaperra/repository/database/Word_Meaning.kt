package com.agaperra.repository.database

import androidx.room.DatabaseView
import androidx.room.Embedded
import androidx.room.Relation
import com.agaperra.repository.database.entity.Meaning
import com.agaperra.repository.database.entity.Word

@DatabaseView
data class Word_Meaning (
    @Embedded val word: Word,
    @Relation(
    parentColumn = "word",
    entityColumn = "parent_word"
)
val meanings: List<Meaning>
)
