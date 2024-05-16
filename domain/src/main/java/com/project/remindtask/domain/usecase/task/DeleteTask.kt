package com.project.remindtask.domain.usecase.task

import com.project.remindtask.domain.repository.TaskRepository
import java.util.UUID

class DeleteTask(
    private val taskRepository: TaskRepository,
) {
    suspend operator fun invoke(id: UUID?) {
        id?.let {
            taskRepository.deleteById(id)
        }
    }
}