package com.thecode.dagger_hilt_mvvm.repository

//import com.thecode.dagger_hilt_mvvm.database.Blog
import android.util.Log
import com.thecode.dagger_hilt_mvvm.database.PostCacheMapper
import com.thecode.dagger_hilt_mvvm.database.PostDao
import com.thecode.dagger_hilt_mvvm.database.UserCacheMapper


import com.thecode.dagger_hilt_mvvm.model.Post
import com.thecode.dagger_hilt_mvvm.network.*


import com.thecode.dagger_hilt_mvvm.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class MainRepository
constructor(
    private val postDao: PostDao,
    private val postApi: PostApi,
    private val postCacheMapper: PostCacheMapper,
    private val postMapper: PostMapper,
    private val userCacheMapper: UserCacheMapper,
    private val userMapper: UserMapper
) {

    private suspend fun getPostFromApi():  List<PostObjectResponse>  =
        try {
          postApi.get()

        }catch (e: Exception){
            listOf()
        }

    private suspend fun getUserFromApi():  List<UserObjectResponse>  =
        try {
            postApi.getAllUsers()

        }catch (e: Exception){
            listOf()
        }

    suspend fun getPost(): Flow<DataState<List<Post>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val networkUsers = getUserFromApi()
            Log.e("USER FROM API TEST ", "$networkUsers")

            if (networkUsers.isNotEmpty()){
                val users = userMapper.mapFromEntityList(networkUsers)
                for (user in users) {
                    postDao.insertUser(userCacheMapper.mapToEntity(user))
                }
                val userCachedBlogs = postDao.getAllUsers()
                Log.e("USER FROM DATABASE",
                    "${userCacheMapper.mapFromEntityList(userCachedBlogs)}")
            }else{
                val userCachedBlogs = postDao.getAllUsers()
                userCacheMapper.mapFromEntityList(userCachedBlogs)
                Log.e("NOCON USER FROM Db",
                    "${userCacheMapper.mapFromEntityList(userCachedBlogs)}")
            }

            val networkPosts = getPostFromApi()
//            Log.e("ok", "${networkPosts}")
            if (networkPosts.isNotEmpty()){
            val posts = postMapper.mapFromEntityList(networkPosts)
            for (post in posts) {
                postDao.insert(postCacheMapper.mapToEntity(post))
            }
                val postCachedBlogs = postDao.get()
                emit(DataState.Success(postCacheMapper.mapFromEntityList(postCachedBlogs)))

            }else{
                val postCachedBlogs = postDao.get()
                emit(DataState.Success(postCacheMapper.mapFromEntityList(postCachedBlogs)))
            }
        }
       catch (e: Exception) {
            Log.e("TAG","$e")
            emit(DataState.Error(e))
        }
    }
}