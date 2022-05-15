package com.thecode.dagger_hilt_mvvm.repository

import android.util.Log
import com.thecode.dagger_hilt_mvvm.database.*
import com.thecode.dagger_hilt_mvvm.model.Comment
import com.thecode.dagger_hilt_mvvm.model.Detail
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
    private val userCacheMapper: UserCacheMapper,
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

    private suspend fun updateUserCache(networkUsers :  List<UserObjectResponse>) {
        try {
            val users = userMapper.mapFromEntityList(networkUsers)
            for (user in users) {
                postDao.insertUser(userCacheMapper.mapToEntity(user))
            }
        }catch (e:Exception){
            Log.e("TAG", "$e")
        }
    }



    suspend fun getDetail(postId : Int ): Flow<DataState<List<Detail>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {

            val networkUsers = getUserFromApi()
            val networkComments = getCommentFromApi()

//            Log.e("USER FROM API TEST ", "$networkUsers")
            Log.e("COMMENTS FROM API TEST ", "$networkUsers")

            if (networkUsers.isNotEmpty() && networkComments.isNotEmpty()){
                updateCommentCache(networkComments)
                updateUserCache(networkUsers)
                val details = postDao.getAllDetails()
                Log.e("DETAIL",
                    "$details")
                emit(DataState.Success(details))

            }else{
                val details = postDao.getAllDetails()
                Log.e("DETAIL FROM DATABASE",
                    "$details")
                emit(DataState.Success(details))
            }
        }
        catch (e: Exception) {
            Log.e("TAG","$e")
            emit(DataState.Error(e))
        }
    }
}

