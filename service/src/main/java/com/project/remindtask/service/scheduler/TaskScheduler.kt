package com.project.remindtask.service.scheduler

import com.project.remindtask.domain.model.Task
import java.util.UUID

interface TaskScheduler {
    fun setTask(task: Task)
    fun cancelTask(id: UUID)
}