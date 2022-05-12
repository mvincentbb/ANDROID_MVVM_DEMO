package com.thecode.dagger_hilt_mvvm.database

import androidx.room.Embedded
import androidx.room.Relation



data class UserWithPosts(
    @Embedded val user : UserCacheEntity,
    @Relation(
        parentColumn ="id",
        entityColumn = "userId"
    )
    val posts : List<PostCacheEntity>
)
