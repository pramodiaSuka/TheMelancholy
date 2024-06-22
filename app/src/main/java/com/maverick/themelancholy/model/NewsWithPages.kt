package com.maverick.themelancholy.model

import androidx.room.Embedded
import androidx.room.Relation

data class NewsWithPages(
    @Embedded val news: News,
    @Relation(
        parentColumn = "id",
        entityColumn = "news_id"
    )
    val pages: List<Page>
)
