package com.thecode.dagger_hilt_mvvm.network

import com.thecode.dagger_hilt_mvvm.network.comment.CommentObjectResponse
import retrofit2.http.GET

interface PostApi {

/*    @GET("blogs")
    suspend fun get(): List<BlogObjectResponse>*/

    @GET("posts")
    suspend fun get(): List<PostObjectResponse>

    @GET("comments")
    suspend fun getAllComments(): List<CommentObjectResponse>

  @GET("users")
  suspend fun getAllUsers(): List<UserObjectResponse>


}