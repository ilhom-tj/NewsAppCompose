package com.example.newsapp.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideNewsDataBase(@ApplicationContext app: Context) =
        Room.databaseBuilder(app,NewsDB::class.java,"news_db").build()

    @Singleton
    @Provides
    fun getNewsDao(db: NewsDB) = db.getDao()
}