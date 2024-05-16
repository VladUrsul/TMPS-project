package com.project.remindtask.domain.usecase.task

data class TaskUseCases(
    val getTasks: GetTasks,
    val addTask: AddTask,
    val checkTask: CheckTask,
    val deleteTask: DeleteTask
)