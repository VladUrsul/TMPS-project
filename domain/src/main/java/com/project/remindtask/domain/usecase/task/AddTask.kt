package com.project.remindtask.domain.usecase.task

import com.project.remindtask.domain.model.InvalidTaskException
import com.project.remindtask.domain.model.Task
import com.project.remindtask.domain.repository.TaskRepository

class AddTask(
    private val taskRepository: TaskRepository,
) {

    @Throws(InvalidTaskException::class)
    suspend operator fun invoke(task: Task) {
        if (task.title.isBlank()) {
            throw InvalidTaskException("Title cannot be empty")
        }

        if (task.deadline == 0L) {
            throw InvalidTaskException("Date and time cannot be empty")
        }

        taskRepository.save(task)
    }
}