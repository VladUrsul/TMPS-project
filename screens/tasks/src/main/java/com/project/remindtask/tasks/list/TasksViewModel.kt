package com.project.remindtask.tasks.list

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.remindtask.domain.model.*
import com.project.remindtask.domain.usecase.task.TaskUseCases
import com.project.remindtask.domain.usecase.user.UserUseCase
import com.project.remindtask.service.TaskNotification
import com.project.remindtask.service.scheduler.TaskScheduler
import com.project.remindtask.tasks.add.AddTaskEvent
import com.project.remindtask.tasks.add.AddTaskState
import com.project.remindtask.tasks.add.AddTaskViewModel.UiEvent
import com.project.remindtask.utils.DateTimeAdapter
import com.project.remindtask.utils.TaskBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.Console
import java.time.LocalDate
import java.util.UUID
import javax.inject.Inject


@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
    private val userUseCase: UserUseCase,
    private val taskScheduler: TaskScheduler,
    private val notificationManager: TaskNotification,
) : ViewModel() {

    private val _state = mutableStateOf(TasksState())
    val state: State<TasksState> = _state

    private var tasks = emptyList<Task>()

    init {
        initTasks()
    }

    fun onEvent(event: TasksEvent) {
        when (event) {
            is TasksEvent.OnStatusSelected -> onStatusSelected(event.status)
            is TasksEvent.OnDateSelected -> onDateSelected(event.date)
            is TasksEvent.OnTimeSelected -> onTimeSelected(event.timeType)
            is TasksEvent.OnHeaderTypeChanged -> onHeaderTypeChanged(event.headerType)
            is TasksEvent.OnTaskClick -> checkTask(event.task)
            is TasksEvent.OnTaskDelete -> deleteTask(event.id)
            is TasksEvent.OnTaskCopy -> copyTask(event.task)
            is TasksEvent.OnShowDialog -> onShowDialog(event.show, event.task)
            is TasksEvent.OnShowSearchBar -> onShowSearchBar(event.show)
            is TasksEvent.OnQueryChange -> onQueryChange(event.query)
        }
    }

    private fun initTasks() {
        viewModelScope.launch {
            taskUseCases.getTasks().collectLatest { tasksList ->
                tasks = tasksList
                userUseCase.getHeaderType().collectLatest {
                    _state.value = _state.value.copy(headerType = it)
                    filterTasks()
                }
            }
        }
    }

    private fun onStatusSelected(status: StatusType?) {
        _state.value = _state.value.copy(status = status)
        filterTasks()
    }

    private fun onDateSelected(date: LocalDate) {
        _state.value = _state.value.copy(selectedDate = date)
        filterTasks()
    }

    private fun onTimeSelected(time: TimeType) {
        _state.value = _state.value.copy(selectedTime = time)
        filterTasks()
    }

    private fun onShowDialog(show: Boolean, task: Task?) {
        _state.value = _state.value.copy(showDialog = show, selectedTask = task)
    }

    private fun onShowSearchBar(show: Boolean) {
        _state.value = _state.value.copy(showSearchBar = show)
    }

    private fun onQueryChange(query: String) {
        _state.value = _state.value.copy(searchQuery = query)
        filterTasks()
    }

    private fun filterTasks() {
        val filteredTasks = taskUseCases.getTasks.filter(
            tasks = tasks,
            headerType = _state.value.headerType,
            selectedDate = _state.value.selectedDate,
            status = _state.value.status,
            timeType = _state.value.selectedTime,
            searchQuery = _state.value.searchQuery
        )
        _state.value = _state.value.copy(tasks = filteredTasks)
    }

    private fun onHeaderTypeChanged(headerType: HeaderType) {
        viewModelScope.launch {
            val type =
                if (headerType == HeaderType.CALENDAR) HeaderType.CHIPS else HeaderType.CALENDAR
            userUseCase.saveHeaderType(type)
            filterTasks()
        }
    }

    private fun checkTask(task: Task) {
        viewModelScope.launch {
            taskUseCases.checkTask(task)
        }

        if (task.status == StatusType.UNCOMPLETED) {
            taskScheduler.cancelTask(task.id ?: UUID.randomUUID())
            notificationManager.cancelNotification(task.id.hashCode())
        }
    }

    private fun deleteTask(id: UUID?) {
        viewModelScope.launch {
            taskUseCases.deleteTask(id)
        }
        taskScheduler.cancelTask(id ?: UUID.randomUUID())
        notificationManager.cancelNotification(id.hashCode())
    }

    private fun copyTask(task: Task) {
        val _state = mutableStateOf(AddTaskState())
        val state: State<AddTaskState> = _state
        val _eventFlow = MutableSharedFlow<UiEvent>()
        viewModelScope.launch {
            try {
                val newTask = task.clone()

                taskUseCases.addTask(newTask)
                taskScheduler.setTask(newTask)

                if (_state.value.isAddMode) {
                    _eventFlow.emit(UiEvent.SaveTask)
                } else {
                    _state.value = state.value.copy(isReadOnly = true)
                }
            } catch (e: Exception) {
                _eventFlow.emit(UiEvent.ShowSnackbar(e.message.toString()))
            }
        }
    }
}