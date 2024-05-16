package com.project.remindtask.domain.usecase.task

import com.project.remindtask.domain.model.Task
import com.project.remindtask.domain.repository.TaskRepository

class CheckTask(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(task: Task) {
        taskRepository.checkTask(task)
    }
}