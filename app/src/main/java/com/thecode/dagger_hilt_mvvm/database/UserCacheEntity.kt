package com.thecode.dagger_hilt_mvvm.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.thecode.dagger_hilt_mvvm.model.Geo

@Entity(tableName = "users")
data class UserCacheEntity (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "phone")
    val phone: String,
    @ColumnInfo(name = "website")
    val website: String,

    @Embedded
    val address: AddressCacheEntity,


    @Embedded
    val company: CompanyCacheEntity
)


@Entity
data class AddressCacheEntity (

    @ColumnInfo(name = "street")
    val street: String,
    @ColumnInfo(name = "suite")
    val suite: String,
    @ColumnInfo(name = "city")
    val city: String,
    val zipcode: String,
    @Embedded
    val geo: Geo


)
/*
@Entity(tableName = "address")
data class Address (
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    @Embedded
    val geo: Geo
)

@Entity(tableName = "company")
data class Company (
    @ColumnInfo(name = "company_name")
    val name: String,
    @ColumnInfo(name = "catchPhrase")
    val catchPhrase: String,
    @ColumnInfo(name = "bs")
    val bs: String
)
@Entity
data class Geo (
    val lat: String,
    val lng: String
)
*/


@Entity(tableName = "company")
data class CompanyCacheEntity (
    @ColumnInfo(name = "company_name")
    val name: String,
    @ColumnInfo(name = "catchPhrase")
    val catchPhrase: String,
    @ColumnInfo(name = "bs")
    val bs: String
)