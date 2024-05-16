package com.project.remindtask.utils

import com.project.remindtask.domain.model.Note
import java.util.UUID

class NoteBuilder {
    private var id: UUID? = null
    private var title: String = ""
    private var description: String = ""
    private var createdAt: Long = 0

    fun id(id: UUID) = apply { this.id = id }

    fun title(title: String) = apply { this.title = title }

    fun description(description: String) = apply { this.description = description }

    fun createdAt(createdAt: Long) = apply { this.createdAt = createdAt }

    fun build(): Note {
        require(title.isNotEmpty()) { "Title must be set" }
        require(description.isNotEmpty()) { "Description must be set" }
        return Note(
            id = id ?: UUID.randomUUID(),
            title = title,
            description = description,
            createdAt = if (createdAt != 0L) createdAt else System.currentTimeMillis()
        )
    }
}