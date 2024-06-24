package com.maverick.themelancholy.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class UsersWithNews(
    @Embedded val user: User,
    @Relation(
        parentColumn = "username",
        entityColumn = "id",
        associateBy = Junction(UserNewsCrossRef::class)
    )
    val news: List<News>
)
