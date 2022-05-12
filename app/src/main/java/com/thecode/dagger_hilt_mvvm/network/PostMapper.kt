package com.thecode.dagger_hilt_mvvm.network

//import com.thecode.dagger_hilt_mvvm.model.Blog
import com.thecode.dagger_hilt_mvvm.model.Post
import com.thecode.dagger_hilt_mvvm.util.EntityMapper
import javax.inject.Inject

class PostMapper
@Inject
constructor() : EntityMapper<PostObjectResponse, Post> {
    override fun mapFromEntity(entity: PostObjectResponse): Post {
        return Post(
            id = entity.id,
            title = entity.title,
            body = entity.body,
            userId = entity.userId
        )
    }

    override fun mapToEntity(domainModel: Post): PostObjectResponse {
        return PostObjectResponse(
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body,
            userId = domainModel.userId
        )
    }

     fun mapFromEntityList(entities: List<PostObjectResponse>): List<Post> {
        return entities.map { mapFromEntity(it) }
    }

}
