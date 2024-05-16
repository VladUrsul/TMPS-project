package com.project.remindtask.domain.usecase.user

import com.project.remindtask.domain.model.NotesType
import com.project.remindtask.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetNotesType(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Flow<NotesType> =
        userRepository.getNotesType()
}
