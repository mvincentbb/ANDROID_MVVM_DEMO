package com.thecode.dagger_hilt_mvvm.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(postEntity: PostCacheEntity): Long

    @Query("SELECT * FROM posts")
    suspend fun get(): List<PostCacheEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComment(commentEntity: CommentCacheEntity): Long

    @Query("SELECT * FROM comments")
    suspend fun getAllComments(): List<CommentCacheEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserCacheEntity): Long

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<UserCacheEntity>


}