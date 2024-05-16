package com.project.remindtask.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.remindtask.domain.usecase.note.NoteUseCases
import com.project.remindtask.domain.usecase.task.TaskUseCases
import com.project.remindtask.utils.DummyData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
    private val notesUseCases: NoteUseCases,
) : ViewModel() {
    fun init() {
        viewModelScope.launch {
            DummyData.generateTasks().map {
                taskUseCases.addTask(it)
            }

            DummyData.generateNotes().map {
                notesUseCases.addNote(it)
            }
        }
    }
}