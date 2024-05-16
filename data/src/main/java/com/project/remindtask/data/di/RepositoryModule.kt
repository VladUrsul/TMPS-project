package com.project.remindtask.data.di

import com.project.remindtask.data.local.db.dao.NoteDao
import com.project.remindtask.data.local.db.dao.TaskDao
import com.project.remindtask.data.local.preferences.UserPreferences
import com.project.remindtask.data.repository.NoteRepositoryImpl
import com.project.remindtask.data.repository.TaskRepositoryImpl
import com.project.remindtask.data.repository.UserRepositoryImpl
import com.project.remindtask.domain.repository.NoteRepository
import com.project.remindtask.domain.repository.TaskRepository
import com.project.remindtask.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideTaskRepository(taskDao: TaskDao): TaskRepository = TaskRepositoryImpl(taskDao)

    @Provides
    @Singleton
    fun provideUserRepository(userPreferences: UserPreferences): UserRepository = UserRepositoryImpl(userPreferences)

    @Provides
    @Singleton
    fun provideNoteRepository(noteDao: NoteDao): NoteRepository = NoteRepositoryImpl(noteDao)
}