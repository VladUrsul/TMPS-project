package com.project.remindtask.domain.usecase.user

import com.project.remindtask.domain.model.HeaderType
import com.project.remindtask.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetHeaderType(
    private val userRepository: UserRepository,
) {
    operator fun invoke(): Flow<HeaderType> {
        return userRepository.getHeaderType()
    }
}