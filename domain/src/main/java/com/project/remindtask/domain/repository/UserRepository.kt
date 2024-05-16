package com.project.remindtask.domain.repository

import com.project.remindtask.domain.model.HeaderType
import com.project.remindtask.domain.model.NotesType
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun saveHeaderType(headerType: HeaderType)
    fun getHeaderType(): Flow<HeaderType>
    suspend fun saveNotesType(notesType: NotesType)
    fun getNotesType(): Flow<NotesType>
}