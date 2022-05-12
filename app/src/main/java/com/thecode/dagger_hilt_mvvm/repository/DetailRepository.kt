package com.thecode.dagger_hilt_mvvm.repository

import android.util.Log
import com.thecode.dagger_hilt_mvvm.database.CommentCacheEntity
import com.thecode.dagger_hilt_mvvm.database.CommentCacheMapper
import com.thecode.dagger_hilt_mvvm.database.PostCacheMapper
import com.thecode.dagger_hilt_mvvm.database.PostDao
import com.thecode.dagger_hilt_mvvm.model.Comment
import com.thecode.dagger_hilt_mvvm.model.Post
import com.thecode.dagger_hilt_mvvm.model.User
import com.thecode.dagger_hilt_mvvm.network.*
import com.thecode.dagger_hilt_mvvm.network.comment.CommentMapper
import com.thecode.dagger_hilt_mvvm.network.comment.CommentObjectResponse

import com.thecode.dagger_hilt_mvvm.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.NetPermission


class DetailRepository
constructor(
    private val postDao: PostDao,
    private val postApi: PostApi,
    private val commentCacheMapper: CommentCacheMapper,
    private val commentMapper: CommentMapper,
    private val userCacheMapper: CommentCacheMapper,
    private val userMapper: UserMapper
) {

    private suspend fun getCommentFromApi():  List<CommentObjectResponse>  =
        try {
            postApi.getAllComments()

        }catch (e: Exception){
            listOf()
        }

    private suspend fun getUserFromApi():  List<UserObjectResponse>  =
        try {
            postApi.getAllUsers()

        }catch (e: Exception){
            listOf()
        }


    private suspend fun updateCommentCache(networkComments :  List<CommentObjectResponse>) {
        try {
            val comments = commentMapper.mapFromEntityList(networkComments)
            for (comment in comments) {
                postDao.insertComment(commentCacheMapper.mapToEntity(comment))
            }
        }catch (e:Exception){
            Log.e("TAG", "$e")
        }
    }



    suspend fun getPost(): Flow<DataState<List<Comment>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val networkComments = getCommentFromApi()
//            Log.e("ok", "${networkPosts}")
            if (networkComments.isNotEmpty()){
              updateCommentCache(networkComments)
                val commentCachedBlogs = postDao.getAllComments()
                emit(DataState.Success(commentCacheMapper.mapFromEntityList(commentCachedBlogs)))

            }else{
                val commentCachedBlogs = postDao.getAllComments()
                emit(DataState.Success(commentCacheMapper.mapFromEntityList(commentCachedBlogs)))
            }

        }
        catch (e: Exception) {
            Log.e("TAG","$e")
            emit(DataState.Error(e))
        }
    }
}

