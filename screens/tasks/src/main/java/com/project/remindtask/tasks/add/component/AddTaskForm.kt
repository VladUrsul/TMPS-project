package com.project.remindtask.tasks.add.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.project.remindtask.resources.R
import com.project.remindtask.domain.model.PriorityType
import com.project.remindtask.ui.DescriptionTextField
import com.project.remindtask.ui.TaskDateButton
import com.project.remindtask.ui.TaskPriorityButton
import com.project.remindtask.ui.TaskTimeButton
import com.project.remindtask.ui.TitleTextField
import com.project.remindtask.theme.spacing

@Composable
fun AddTaskForm(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    selectedDate: Long?,
    selectedHour: Int?,
    selectedMinute: Int?,
    priorityType: PriorityType,
    isReadOnly: Boolean,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onPrioritySelect: (PriorityType) -> Unit,
    onDatePickerOpen: () -> Unit,
    onTimePickerOpen: () -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        TitleTextField(
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small),
            value = title,
            hint = stringResource(R.string.add_title),
            readOnly = isReadOnly,
            onValueChange = { onTitleChange(it) }
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        DescriptionTextField(
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small),
            value = description,
            hint = stringResource(R.string.add_description),
            readOnly = isReadOnly,
            onValueChange = { onDescriptionChange(it) }
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
        TaskDateButton(
            selectedDate = selectedDate,
            readOnly = isReadOnly,
            onClick = onDatePickerOpen
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
        TaskTimeButton(
            selectedHour = selectedHour,
            selectedMinute = selectedMinute,
            readOnly = isReadOnly,
            onClick = onTimePickerOpen
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
        TaskPriorityButton(
            selected = priorityType,
            items = PriorityType.getList(),
            readOnly = isReadOnly,
            onItemSelection = {
                onPrioritySelect(it)
            }
        )
    }
}