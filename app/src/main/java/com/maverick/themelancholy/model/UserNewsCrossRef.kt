package com.maverick.themelancholy.model

import androidx.room.Entity

@Entity(primaryKeys = ["username", "id"])
data class UserNewsCrossRef(
    var username: String,
    var id:Int
)
