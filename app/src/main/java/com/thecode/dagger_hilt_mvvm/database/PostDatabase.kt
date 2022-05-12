package com.thecode.dagger_hilt_mvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [PostCacheEntity::class, CommentCacheEntity::class, UserCacheEntity::class], version = 1)
abstract class PostDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao

 /*   companion object {
        private var INSTANCE: PostDatabase? = null
        fun getInstance(context: Context): PostDatabase?{
            if(INSTANCE == null){
                synchronized(PostDatabase::class)()
            }
        }
         val DATABASE_NAME: String = "post_db"
    }*/

   /* companion object {
        const val DATABASE_NAME: String = "blog_db"
    }*/

   /* companion object {
        private var INSTANCE: PostDatabase? = null

        fun getInstance(context: Context): PostDatabase? {
            if (INSTANCE == null) {
                synchronized(PostDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        PostDatabase::class.java, "blog_db").allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }*/
}