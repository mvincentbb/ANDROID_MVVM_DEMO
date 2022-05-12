package com.thecode.dagger_hilt_mvvm.database


    import com.thecode.dagger_hilt_mvvm.model.Address
    import com.thecode.dagger_hilt_mvvm.model.Company
    import com.thecode.dagger_hilt_mvvm.model.User
    import com.thecode.dagger_hilt_mvvm.util.EntityMapper
    import javax.inject.Inject

class UserCacheMapper
@Inject
constructor() : EntityMapper<UserCacheEntity, User> {
    override fun mapFromEntity(entity: UserCacheEntity): User {



        val companyEntity = companyMapFromEntity(entity)
        val addressEntity = addressMapFromEntity(entity)

        return User(
            id = entity.id,
        name = entity.name,
         username = entity.username,
         email = entity.email,
         phone = entity.phone,

         website = entity.website,
            address =  addressEntity,
         company = companyEntity
        )
    }

    override fun mapToEntity(domainModel: User): UserCacheEntity {

  val companyModel = companyMapToEntity(domainModel)
  val addressModel = addressMapToEntity(domainModel)
        return UserCacheEntity(
            id = domainModel.id,
            name = domainModel.name,
            username = domainModel.username,
            email = domainModel.email,
            address = addressModel,
            phone = domainModel.phone,
            website = domainModel.website,
            company = companyModel

        )
    }

     fun mapFromEntityList(entities: List<UserCacheEntity>): List<User> {
        return entities.map { mapFromEntity(it) }
    }


    fun companyMapFromEntity(entity: UserCacheEntity) : Company {
        val companyCacheEntity = CompanyCacheEntity(
            name = entity.company.name,
            catchPhrase = entity.company.catchPhrase,
            bs = entity.company.bs
        )
        return Company(
            name = companyCacheEntity.name,
            catchPhrase = companyCacheEntity.catchPhrase,
            bs = companyCacheEntity.bs
        )
    }

    fun companyMapToEntity(domainModel: User) : CompanyCacheEntity {
        val company = Company(
            name = domainModel.company.name,
            catchPhrase = domainModel.company.catchPhrase,
            bs = domainModel.company.bs
        )
       return CompanyCacheEntity(
            name = company.name,
            catchPhrase = company.catchPhrase,
            bs = company.bs
        )

    }

    fun addressMapFromEntity(entity: UserCacheEntity) : Address {
        val addressCacheEntity = AddressCacheEntity(
            street = entity.address.street,
            suite = entity.address.suite,
            city = entity.address.city,
            zipcode = entity.address.zipcode,
            geo = entity.address.geo

        )
        return Address(
            street = addressCacheEntity.street,
            suite = addressCacheEntity.suite,
            city = addressCacheEntity.city,
            zipcode = addressCacheEntity.zipcode,
            geo = addressCacheEntity.geo
        )
    }

    fun addressMapToEntity(domainModel: User) : AddressCacheEntity {
        val address = Address(
            street = domainModel.address.street,
            suite = domainModel.address.suite,
            city = domainModel.address.city,
            zipcode = domainModel.address.zipcode,
            geo = domainModel.address.geo
        )
       return AddressCacheEntity(
           street = address.street,
               suite = address.suite,
           city = address.city,
           zipcode = address.zipcode,
               geo = address.geo
       )
    }

}