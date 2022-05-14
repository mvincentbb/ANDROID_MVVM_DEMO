package com.thecode.dagger_hilt_mvvm.network.comment


    import com.thecode.dagger_hilt_mvvm.database.PostCacheEntity
    import com.thecode.dagger_hilt_mvvm.model.Comment
    import com.thecode.dagger_hilt_mvvm.model.Post
    import com.thecode.dagger_hilt_mvvm.util.EntityMapper
    import javax.inject.Inject


class CommentMapper
@Inject
constructor() : EntityMapper<CommentObjectResponse, Comment> {
    override fun mapFromEntity(entity: CommentObjectResponse): Comment {
        return Comment(
            id = entity.id,
            postId = entity.postId,
            name = entity.name,
            email  = entity.email,
           body = entity.body
        )
    }

    override fun mapToEntity(domainModel: Comment): CommentObjectResponse {
        return CommentObjectResponse(
            id = domainModel.id,
            postId = domainModel.postId,
            name = domainModel.name,
            email  = domainModel.email,
            body = domainModel.body

        )
    }

     fun mapFromEntityList(entities: List<CommentObjectResponse>): List<Comment> {
        return entities.map { mapFromEntity(it) }
    }


}


