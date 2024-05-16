package com.project.remindtask.data.repository

import com.project.remindtask.data.local.db.dao.NoteDao
import com.project.remindtask.data.repository.mapper.NoteMapper.toDomain
import com.project.remindtask.data.repository.mapper.NoteMapper.toEntity
import com.project.remindtask.domain.model.Note
import com.project.remindtask.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao,
) : NoteRepository {
    override fun getAll(): Flow<List<Note>> {
        return noteDao.getAll().map {
            it.toDomain()
        }
    }

    override suspend fun save(note: Note) {
        noteDao.save(note.toEntity())
    }

    override suspend fun deleteById(id: UUID) {
        noteDao.deleteById(id)
    }

    override suspend fun getById(id: UUID): Note? {
        return noteDao.getById(id)?.toDomain()
    }
}