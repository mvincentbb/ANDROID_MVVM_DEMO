package com.thecode.dagger_hilt_mvvm.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thecode.dagger_hilt_mvvm.model.Detail

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(postEntity: PostCacheEntity): Long

    @Query("SELECT * FROM posts")
    suspend fun get(): List<PostCacheEntity>

    @Insert(entity = CommentCacheEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComment(commentEntity: CommentCacheEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(vararg commentEntities: CommentCacheEntity)


    @Query("SELECT * FROM comments")
    suspend fun getAllComments(): List<CommentCacheEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserCacheEntity): Long

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<UserCacheEntity>

    @Query("SELECT COUNT(*) as commentsNumber, posts.body as postDescription, users.username as authorName " +
            "FROM users INNER JOIN  posts on users.id = posts.userId " +
            "INNER JOIN comments on comments.postId = posts.id  " +
            "WHERE postId = 1")
    suspend fun getAllDetails(): List<Detail>


}