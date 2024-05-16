package com.project.remindtask.domain.usecase.note

import com.project.remindtask.domain.model.InvalidNoteException
import com.project.remindtask.domain.model.Note
import com.project.remindtask.domain.repository.NoteRepository

class AddNote(
    private val noteRepository: NoteRepository,
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank() && note.description.isBlank()) {
            throw InvalidNoteException("Title and description cannot be empty. Please fill one of them.")
        }
        noteRepository.save(note)
    }
}
