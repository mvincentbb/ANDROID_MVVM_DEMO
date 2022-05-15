package com.thecode.dagger_hilt_mvvm.di

//import com.thecode.dagger_hilt_mvvm.database.BlogDao
//import com.thecode.dagger_hilt_mvvm.database.CacheMapper
import com.thecode.dagger_hilt_mvvm.database.CommentCacheMapper
import com.thecode.dagger_hilt_mvvm.database.PostCacheMapper
import com.thecode.dagger_hilt_mvvm.database.PostDao
import com.thecode.dagger_hilt_mvvm.database.UserCacheMapper
import com.thecode.dagger_hilt_mvvm.model.Detail
//import com.thecode.dagger_hilt_mvvm.network.BlogApi
//import com.thecode.dagger_hilt_mvvm.network.BlogMapper
import com.thecode.dagger_hilt_mvvm.network.PostApi
import com.thecode.dagger_hilt_mvvm.network.PostMapper
import com.thecode.dagger_hilt_mvvm.network.UserMapper
import com.thecode.dagger_hilt_mvvm.network.comment.CommentMapper
import com.thecode.dagger_hilt_mvvm.repository.DetailRepository

import com.thecode.dagger_hilt_mvvm.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        postDao: PostDao,
        postApi: PostApi,
        postCacheMapper: PostCacheMapper,
        postMapper: PostMapper,
        commentCacheMapper: CommentCacheMapper,
        commentMapper: CommentMapper,
        userCacheMapper: UserCacheMapper,
        userMapper: UserMapper

    ): MainRepository {
        return MainRepository(postDao, postApi, postCacheMapper, postMapper, commentCacheMapper, commentMapper, userCacheMapper, userMapper)
    }

    @Singleton
    @Provides
    fun provideDetailRepository(
        postDao: PostDao,
        postApi: PostApi,
        commentCacheMapper: CommentCacheMapper,
        commentMapper: CommentMapper,
        userCacheMapper: UserCacheMapper,
        userMapper: UserMapper

    ): DetailRepository  {
        return DetailRepository(postDao, postApi, commentCacheMapper, commentMapper, userCacheMapper, userMapper)
    }

}