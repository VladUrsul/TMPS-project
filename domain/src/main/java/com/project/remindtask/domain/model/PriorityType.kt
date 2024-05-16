package com.project.remindtask.domain.model

import androidx.compose.ui.graphics.Color
import com.project.remindtask.theme.color.MainColor

enum class PriorityType {
    LOW,
    MEDIUM,
    HIGH;

    companion object {
        fun PriorityType.toColor(): Color {
            return when (this) {
                HIGH -> MainColor.Red.kindaLight
                MEDIUM -> MainColor.Yellow.kindaLight
                LOW -> MainColor.Green.kindaLight
            }
        }

        fun getList(): List<PriorityType> {
            return listOf(LOW, MEDIUM, HIGH)
        }
    }
}