package com.thecode.dagger_hilt_mvvm.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class UserObjectResponse(

    @SerializedName("id")
    @Expose
    val id: Long,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("username")
    @Expose
    val username: String,

    @SerializedName("email")
    @Expose
    val email: String,

    @SerializedName("address")
    @Expose
    val address: AddressResponse,

    @SerializedName("phone")
    @Expose
    val phone: String,

    @SerializedName("website")
    @Expose
    val website: String,

    @SerializedName("company")
    @Expose
    val company: CompanyResponse

)



data class AddressResponse (
    @SerializedName("street")
    @Expose
    val street: String,
    @SerializedName("suite")
    @Expose
    val suite: String,
    @SerializedName("city")
    @Expose
    val city: String,
    @SerializedName("zipcode")
    @Expose
    val zipcode: String,
    @SerializedName("geo")
    @Expose
    val geo: GeoResponse
)

data class GeoResponse (
    @SerializedName("lat")
    @Expose
    val lat: String,
    @SerializedName("lng")
    @Expose
    val lng: String
)

data class CompanyResponse (
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("catchPhrase")
    @Expose
    val catchPhrase: String,
    @SerializedName("bs")
    @Expose
    val bs: String
)



