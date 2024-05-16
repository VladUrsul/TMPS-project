package com.project.remindtask.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Task(
    val id: UUID? = null,
    val title: String,
    val description: String?,
    val deadline: Long,
    val priority: PriorityType,
    val status: StatusType,
) : Parcelable {
    fun clone(): Task {
        return this.copy(id = UUID.randomUUID())
    }
}

class InvalidTaskException(message: String) : Exception(message)
