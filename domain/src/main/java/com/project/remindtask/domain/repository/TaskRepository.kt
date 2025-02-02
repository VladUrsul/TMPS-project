package com.project.remindtask.domain.repository

import com.project.remindtask.domain.model.Task
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface TaskRepository {
    fun getAll(): Flow<List<Task>>
    suspend fun save(task: Task)
    suspend fun deleteById(id: UUID)
    suspend fun getById(id: UUID): Task?
    suspend fun checkTask(task: Task)
}