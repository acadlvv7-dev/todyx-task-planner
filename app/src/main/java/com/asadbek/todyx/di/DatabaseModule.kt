package com.asadbek.todyx.di

import android.content.Context
import androidx.room.Room
import com.asadbek.todyx.data.locall.StudyFlowDatabase
import com.asadbek.todyx.data.locall.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): StudyFlowDatabase {
        return Room.databaseBuilder(
            context,
            StudyFlowDatabase::class.java,
            "studyflow_database"
        ).build()
    }

    @Provides
    fun provideTaskDao(
        database: StudyFlowDatabase
    ): TaskDao {
        return database.taskDao()
    }
}
