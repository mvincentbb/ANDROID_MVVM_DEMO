package com.thecode.dagger_hilt_mvvm.network

import com.thecode.dagger_hilt_mvvm.model.Address
import com.thecode.dagger_hilt_mvvm.model.Geo
import com.thecode.dagger_hilt_mvvm.model.User
import com.thecode.dagger_hilt_mvvm.util.EntityMapper
import javax.inject.Inject

class AddressMapper
@Inject
constructor() : EntityMapper<AddressResponse, Address> {
    override fun mapFromEntity(entity: AddressResponse): Address {
        return Address(
            street = entity.street,
            suite = entity.suite,
            city =  entity.city,
            zipcode = entity.zipcode,
            geo = Geo(lat = entity.geo.lat, lng = entity.geo.lng)
        )
    }
    override fun mapToEntity(domainModel: Address): AddressResponse {
        return AddressResponse(
            street = domainModel.street,
            suite = domainModel.suite,
            city =  domainModel.city,
            zipcode = domainModel.zipcode,
            geo = GeoResponse(lat = domainModel.geo.lat, lng = domainModel.geo.lng)

        )
    }

}