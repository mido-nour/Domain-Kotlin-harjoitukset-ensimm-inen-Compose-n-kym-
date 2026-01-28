package com.example.myapplication.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.model.Priority
import com.example.myapplication.model.Task
import com.example.myapplication.viewmodel.TaskViewModel
import androidx.compose.foundation.layout.statusBarsPadding


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(vm: TaskViewModel = viewModel()) {

    val tasks by vm.tasks.collectAsState()
    var newTaskTitle by remember { mutableStateOf("") }
    var editingTask by remember { mutableStateOf<Task?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .statusBarsPadding()
    ) {

        Text(
            text = "Tasks (${tasks.size})",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            TextField(
                value = newTaskTitle,
                onValueChange = { newTaskTitle = it },
                modifier = Modifier.weight(1f),
                label = { Text("New task") },
                singleLine = true
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                val title = newTaskTitle.trim()
                if (title.isNotEmpty()) {
                    val nextId = (tasks.maxOfOrNull { it.id } ?: 0) + 1

                    vm.addTask(
                        Task(
                            id = nextId,
                            title = title,
                            description = "",
                            priority = Priority.LOW,
                            dueDate = "2026-01-20",
                            done = false
                        )
                    )

                    newTaskTitle = ""
                }
            }) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(onClick = { vm.sortByDueDate() }) {
                Text("Sort")
            }
            OutlinedButton(onClick = { vm.showAll() }) {
                Text("All")
            }
            OutlinedButton(onClick = { vm.filterByDone(true) }) {
                Text("Done")
            }
            OutlinedButton(onClick = { vm.filterByDone(false) }) {
                Text("Not done")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))


        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tasks) { task ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { editingTask = task }
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Checkbox(
                            checked = task.done,
                            onCheckedChange = { vm.toggleDone(task.id) }
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = task.title,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Text(
                                text = task.description.ifBlank { "No description" },
                                style = MaterialTheme.typography.bodySmall
                            )

                            Text(
                                text = "Due: ${task.dueDate} • ${task.priority}",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }

                        TextButton(onClick = { vm.removeTask(task.id) }) {
                            Text("Delete")
                        }
                    }
                }
            }
        }
    }


    editingTask?.let { task ->
        TaskEditDialog(
            task = task,
            onCancel = { editingTask = null },
            onSave = { updatedTask ->
                vm.updateTask(updatedTask)
                editingTask = null
            },
            onDelete = {
                vm.removeTask(task.id)
                editingTask = null
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TaskEditDialog(
    task: Task,
    onCancel: () -> Unit,
    onSave: (Task) -> Unit,
    onDelete: () -> Unit
) {
    var title by remember(task.id) { mutableStateOf(task.title) }
    var description by remember(task.id) { mutableStateOf(task.description) }
    var dueDate by remember(task.id) { mutableStateOf(task.dueDate) }
    var priority by remember(task.id) { mutableStateOf(task.priority) }
    var priorityMenuOpen by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text("Edit task") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {

                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = dueDate,
                    onValueChange = { dueDate = it },
                    label = { Text("Due date (YYYY-MM-DD)") },
                    modifier = Modifier.fillMaxWidth()
                )

                ExposedDropdownMenuBox(
                    expanded = priorityMenuOpen,
                    onExpandedChange = { priorityMenuOpen = !priorityMenuOpen }
                ) {
                    OutlinedTextField(
                        value = priority.name,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Priority") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = priorityMenuOpen,
                        onDismissRequest = { priorityMenuOpen = false }
                    ) {
                        Priority.entries.forEach { p ->
                            DropdownMenuItem(
                                text = { Text(p.name) },
                                onClick = {
                                    priority = p
                                    priorityMenuOpen = false
                                }
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                if (title.trim().isNotEmpty()) {
                    onSave(
                        task.copy(
                            title = title.trim(),
                            description = description.trim(),
                            dueDate = dueDate.trim(),
                            priority = priority
                        )
                    )
                }
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = onCancel) {
                    Text("Cancel")
                }
                TextButton(onClick = onDelete) {
                    Text("Delete")
                }
            }
        }
    )
}
