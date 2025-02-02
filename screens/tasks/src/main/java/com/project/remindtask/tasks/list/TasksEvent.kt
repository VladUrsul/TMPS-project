package com.project.remindtask.tasks.list

import com.project.remindtask.domain.model.HeaderType
import com.project.remindtask.domain.model.StatusType
import com.project.remindtask.domain.model.Task
import com.project.remindtask.domain.model.TimeType
import java.time.LocalDate
import java.util.UUID

sealed class TasksEvent {
    data class OnStatusSelected(val status: StatusType?) : TasksEvent()
    data class OnDateSelected(val date: LocalDate) : TasksEvent()
    data class OnTimeSelected(val timeType: TimeType) : TasksEvent()
    data class OnHeaderTypeChanged(val headerType: HeaderType) : TasksEvent()
    data class OnTaskClick(val task: Task) : TasksEvent()
    data class OnTaskDelete(val id: UUID?) : TasksEvent()
    data class OnTaskCopy(val task: Task) : TasksEvent()
    data class OnShowDialog(val show: Boolean, val task: Task? = null) : TasksEvent()
    data class OnShowSearchBar(val show: Boolean) : TasksEvent()
    data class OnQueryChange(val query: String) : TasksEvent()
}