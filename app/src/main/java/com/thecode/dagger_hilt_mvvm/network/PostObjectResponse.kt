package com.thecode.dagger_hilt_mvvm.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostObjectResponse(

    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("userId")
    @Expose
    var userId: Int,

    @SerializedName("title")
    @Expose
    var title: String,

    @SerializedName("body")
    @Expose
    var body: String

)