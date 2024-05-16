package com.project.remindtask.domain.usecase.note

import com.project.remindtask.domain.repository.NoteRepository
import java.util.UUID

class DeleteNote(
    private val noteRepository: NoteRepository,
) {
    suspend operator fun invoke(id: UUID?) {
        id?.let {
            noteRepository.deleteById(id)
        }
    }
}
