package com.project.remindtask.notes

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.project.remindtask.domain.model.Note
import com.project.remindtask.notes.list.NotesEvent
import com.project.remindtask.notes.list.NotesScreen
import com.project.remindtask.notes.list.NotesViewModel

fun NavGraphBuilder.notesScreen(
    navigateToEditNote: (Note) -> Unit,
) {
    composable(route = com.project.remindtask.navigation.Screens.Notes.route) {
        val viewModel = hiltViewModel<NotesViewModel>()
        val state = viewModel.state.value
        val event = viewModel::onEvent

        NotesScreen(
            state = state,
            onNotesTypeChange = {
                event(
                    NotesEvent.OnNotesTypeChange(
                        it
                    )
                )
            },
            onSearchQueryChange = {
                event(
                    NotesEvent.OnSearchQueryChange(
                        it
                    )
                )
            },
            onShowSearchBar = {
                event(
                    NotesEvent.OnShowSearchBarChange(
                        it
                    )
                )
            },
            onNoteClick = { navigateToEditNote(it) }
        )
    }
}