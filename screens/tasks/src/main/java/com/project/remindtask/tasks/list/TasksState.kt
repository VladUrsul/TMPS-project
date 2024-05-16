package com.project.remindtask.tasks.list

import com.project.remindtask.domain.model.HeaderType
import com.project.remindtask.domain.model.Task
import com.project.remindtask.domain.model.StatusType
import com.project.remindtask.domain.model.TimeType
import java.time.LocalDate

data class TasksState(
    val tasks: List<Task> = emptyList(),
    val status : StatusType? = null,
    val selectedDate: LocalDate = LocalDate.now(),
    val selectedTime: TimeType = TimeType.TODAY,
    val headerType: HeaderType = HeaderType.CALENDAR,
    val showDialog : Boolean = false,
    val selectedTask: Task? = null,
    val showSearchBar : Boolean = false,
    val searchQuery : String = ""
)