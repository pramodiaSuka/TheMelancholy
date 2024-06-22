package com.maverick.themelancholy.model

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithNews(
    @Embedded val user: User,
    @Relation(
        parentColumn = "username",
        entityColumn = "users_username"
    )
    val news: List<News>
)
