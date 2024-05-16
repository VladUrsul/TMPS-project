package com.project.remindtask.notes.list

import com.project.remindtask.domain.model.Note
import com.project.remindtask.domain.model.NotesType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val notesType: NotesType = NotesType.LIST,
    val searchQuery: String = "",
    val showSearchBar: Boolean = false
)
