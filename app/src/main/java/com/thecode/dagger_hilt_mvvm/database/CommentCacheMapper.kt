package com.thecode.dagger_hilt_mvvm.database


    import com.thecode.dagger_hilt_mvvm.model.Comment

    import com.thecode.dagger_hilt_mvvm.util.EntityMapper

    import javax.inject.Inject


class CommentCacheMapper
@Inject
constructor() : EntityMapper<CommentCacheEntity, Comment> {
    override fun mapFromEntity(entity: CommentCacheEntity): Comment {
        return Comment(
            id = entity.id,
            postId = entity.postId,
            name = entity.name,
            email = entity.email,
            body = entity.body
        )
    }

    override fun mapToEntity(domainModel: Comment): CommentCacheEntity {
        return CommentCacheEntity(
            id = domainModel.id,
            postId = domainModel.postId,
            name = domainModel.name,
            email  = domainModel.email,
            body = domainModel.body

        )
    }

    fun mapFromEntityList(entities: List<CommentCacheEntity>): List<Comment> {
        return entities.map { mapFromEntity(it) }
    }

}


