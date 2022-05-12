package com.thecode.dagger_hilt_mvvm.network

import com.thecode.dagger_hilt_mvvm.model.Address
import com.thecode.dagger_hilt_mvvm.model.Geo
import com.thecode.dagger_hilt_mvvm.model.User
import com.thecode.dagger_hilt_mvvm.util.EntityMapper
import javax.inject.Inject

class GeoMapper
@Inject
constructor() : EntityMapper<GeoResponse, Geo> {
    override fun mapFromEntity(entity: GeoResponse): Geo {
        return Geo(
            lat = entity.lat,
            lng = entity.lng
        )
    }
    override fun mapToEntity(domainModel: Geo): GeoResponse {
        return GeoResponse(
            lat = domainModel.lat,
            lng = domainModel.lng

        )
    }

}