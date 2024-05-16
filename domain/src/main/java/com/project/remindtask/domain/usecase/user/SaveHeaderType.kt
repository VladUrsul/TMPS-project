package com.project.remindtask.domain.usecase.user

import com.project.remindtask.domain.model.HeaderType
import com.project.remindtask.domain.repository.UserRepository

class SaveHeaderType(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(headerType: HeaderType) {
        userRepository.saveHeaderType(headerType)
    }
}