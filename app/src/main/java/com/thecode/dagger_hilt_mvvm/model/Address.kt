package com.thecode.dagger_hilt_mvvm.model

data class Address (
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: Geo
)