package com.project.remindtask.domain.di

import com.project.remindtask.domain.repository.NoteRepository
import com.project.remindtask.domain.repository.TaskRepository
import com.project.remindtask.domain.repository.UserRepository
import com.project.remindtask.domain.usecase.note.AddNote
import com.project.remindtask.domain.usecase.note.DeleteNote
import com.project.remindtask.domain.usecase.note.GetNotes
import com.project.remindtask.domain.usecase.note.NoteUseCases
import com.project.remindtask.domain.usecase.task.AddTask
import com.project.remindtask.domain.usecase.task.CheckTask
import com.project.remindtask.domain.usecase.task.DeleteTask
import com.project.remindtask.domain.usecase.task.GetTasks
import com.project.remindtask.domain.usecase.task.TaskUseCases
import com.project.remindtask.domain.usecase.user.GetHeaderType
import com.project.remindtask.domain.usecase.user.GetNotesType
import com.project.remindtask.domain.usecase.user.SaveHeaderType
import com.project.remindtask.domain.usecase.user.SaveNotesType
import com.project.remindtask.domain.usecase.user.UserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideTasksUseCase(
        taskRepository: TaskRepository,
    ): TaskUseCases = TaskUseCases(
        getTasks = GetTasks(taskRepository),
        addTask = AddTask(taskRepository),
        checkTask = CheckTask(taskRepository),
        deleteTask = DeleteTask(taskRepository)
    )

    @Provides
    fun provideUsersUseCase(
        userRepository: UserRepository,
    ): UserUseCase = UserUseCase(
        getHeaderType = GetHeaderType(userRepository),
        saveHeaderType = SaveHeaderType(userRepository),
        getNotesType = GetNotesType(userRepository),
        saveNotesType = SaveNotesType(userRepository)
    )

    @Provides
    fun provideNotesUseCase(
        noteRepository: NoteRepository,
    ): NoteUseCases = NoteUseCases(
        getNotes = GetNotes(noteRepository),
        addNote = AddNote(noteRepository),
        deleteNote = DeleteNote(noteRepository)
    )
}