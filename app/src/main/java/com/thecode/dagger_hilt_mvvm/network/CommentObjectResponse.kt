package com.thecode.dagger_hilt_mvvm.network.comment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CommentObjectResponse(

    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("postId")
    @Expose
    val postId: Int,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("email")
    @Expose
    val email : String,
    @SerializedName("body")
    @Expose
    val body: String


)
