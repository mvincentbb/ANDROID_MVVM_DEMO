package com.thecode.dagger_hilt_mvvm.di

import android.content.Context
import androidx.room.Room
//import com.thecode.dagger_hilt_mvvm.database.BlogDao
//import com.thecode.dagger_hilt_mvvm.database.BlogDatabase
import com.thecode.dagger_hilt_mvvm.database.PostDao
import com.thecode.dagger_hilt_mvvm.database.PostDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DataModule {

    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context): PostDatabase {
       return Room.databaseBuilder(
            context, PostDatabase::class.java,
            "post_db"
        )        .build()
    }

    @Singleton
    @Provides
    fun provideBlogDAO(postDatabase: PostDatabase): PostDao {
        return postDatabase.postDao()
    }


}