package com.project.remindtask.utils

import com.project.remindtask.domain.model.PriorityType
import com.project.remindtask.domain.model.StatusType
import com.project.remindtask.domain.model.Task
import java.util.UUID

class TaskBuilder {
    private var id: UUID? = null
    private var title: String = ""
    private var description: String? = null
    private var deadline: Long = 0
    private var priority: PriorityType? = null
    private var status: StatusType? = null

    fun id(id: UUID) = apply { this.id = id }

    fun title(title: String) = apply { this.title = title }

    fun description(description: String?) = apply { this.description = description }

    fun deadline(deadline: Long) = apply { this.deadline = deadline }

    fun priority(priority: PriorityType) = apply { this.priority = priority }

    fun status(status: StatusType) = apply { this.status = status }

    fun build(): Task {
        require(title.isNotEmpty()) { "Title must be set" }
        requireNotNull(priority) { "Priority must be set" }
        requireNotNull(status) { "Status must be set" }
        return Task(
            id = id ?: UUID.randomUUID(),
            title = title,
            description = description,
            deadline = deadline,
            priority = priority!!,
            status = status!!
        )
    }
}