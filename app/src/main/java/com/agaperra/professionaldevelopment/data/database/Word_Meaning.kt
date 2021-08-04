package com.agaperra.professionaldevelopment.data.database

import androidx.room.DatabaseView
import androidx.room.Embedded
import androidx.room.Relation
import com.agaperra.professionaldevelopment.data.database.entity.Meaning
import com.agaperra.professionaldevelopment.data.database.entity.Word

@DatabaseView
data class Word_Meaning (
    @Embedded val word: Word,
    @Relation(
    parentColumn = "word",
    entityColumn = "parent_word"
)
val meanings: List<Meaning>
)
