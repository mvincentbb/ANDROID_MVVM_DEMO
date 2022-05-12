package com.thecode.dagger_hilt_mvvm.model

import com.thecode.dagger_hilt_mvvm.model.Address
import com.thecode.dagger_hilt_mvvm.model.Company


data class User(
    val id: Long,
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company

    )






