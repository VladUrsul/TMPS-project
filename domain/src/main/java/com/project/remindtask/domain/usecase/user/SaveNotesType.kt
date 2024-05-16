package com.project.remindtask.domain.usecase.user

import com.project.remindtask.domain.model.NotesType
import com.project.remindtask.domain.repository.UserRepository

class SaveNotesType(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(notesType: NotesType) =
        userRepository.saveNotesType(notesType)
}
