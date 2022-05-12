package com.thecode.dagger_hilt_mvvm.database

import com.thecode.dagger_hilt_mvvm.model.Blog
import com.thecode.dagger_hilt_mvvm.model.Post
import com.thecode.dagger_hilt_mvvm.util.EntityMapper
import javax.inject.Inject

class PostCacheMapper
@Inject
constructor() : EntityMapper<PostCacheEntity, Post> {
    override fun mapFromEntity(entity: PostCacheEntity): Post {
        return Post(
            id = entity.id,
            userId = entity.userId,
            title = entity.title,
            body = entity.body
        )
    }

    override fun mapToEntity(domainModel: Post): PostCacheEntity {
        return PostCacheEntity(
            id = domainModel.id,
            userId = domainModel.userId,
            title = domainModel.title,
            body = domainModel.body
        )
    }

    fun mapFromEntityList(entities: List<PostCacheEntity>): List<Post> {
        return entities.map { mapFromEntity(it) }
    }

}