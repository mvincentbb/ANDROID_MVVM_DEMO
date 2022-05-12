package com.thecode.dagger_hilt_mvvm.network


    import com.thecode.dagger_hilt_mvvm.model.Address
    import com.thecode.dagger_hilt_mvvm.model.Company
    import com.thecode.dagger_hilt_mvvm.model.Geo
    import com.thecode.dagger_hilt_mvvm.model.User
    import com.thecode.dagger_hilt_mvvm.util.EntityMapper
    import javax.inject.Inject

class UserMapper
@Inject
constructor() : EntityMapper<UserObjectResponse, User> {
    override fun mapFromEntity(entity: UserObjectResponse): User {
        return User(
            id = entity.id,
        name = entity.name,
         username = entity.username,
         email = entity.email,
            address =  addressMapFromEntity(entity.address),
         phone = entity.phone,
         website = entity.website,
         company = companyMapFromEntity(entity.company)
        )
    }
    override fun mapToEntity(domainModel: User): UserObjectResponse {
        return UserObjectResponse(
            id = domainModel.id,
            name = domainModel.name,
            username = domainModel.username,
            email = domainModel.email,
            address = addressMapToEntity(domainModel.address) ,
            phone = domainModel.phone,
            website = domainModel.website,
            company = companyMapToEntity(domainModel.company)

        )
    }
     fun mapFromEntityList(entities: List<UserObjectResponse>): List<User> {
        return entities.map { mapFromEntity(it) }
    }

     private fun addressMapToEntity(domainModel: Address): AddressResponse {
        return AddressResponse(
            street = domainModel.street,
            suite = domainModel.suite,
            city =  domainModel.city,
            zipcode = domainModel.zipcode,
            geo = GeoResponse(lat = domainModel.geo.lat, lng = domainModel.geo.lng)

        )
    }
     private fun addressMapFromEntity(entity: AddressResponse): Address {
        return Address(
            street = entity.street,
            suite = entity.suite,
            city =  entity.city,
            zipcode = entity.zipcode,
            geo = Geo(lat = entity.geo.lat, lng = entity.geo.lng)
        )
    }
     private fun companyMapToEntity(domainModel: Company): CompanyResponse {
        return CompanyResponse(
            name = domainModel.name,
            bs = domainModel.bs,
            catchPhrase = domainModel.catchPhrase
        )
    }
     private fun companyMapFromEntity(entity: CompanyResponse): Company {
        return Company(
            name = entity.name,
            bs = entity.bs,
            catchPhrase = entity.catchPhrase
        )
    }
}


