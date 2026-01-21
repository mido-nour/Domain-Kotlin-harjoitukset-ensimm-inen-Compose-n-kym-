package com.example.myapplication.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.Task
import com.example.myapplication.domain.mockTasks

class TaskViewModel : ViewModel() {

    var tasks by mutableStateOf(listOf<Task>())
        private set

    private var allTasks: List<Task> = emptyList()

    init {
        allTasks = mockTasks
        tasks = allTasks
    }

    fun addTask(task: Task) {
        allTasks = allTasks + task
        tasks = allTasks
    }

    fun toggleDone(id: Int) {
        allTasks = allTasks.map { task ->
            if (task.id == id) task.copy(done = !task.done) else task
        }
        tasks = allTasks
    }

    fun removeTask(id: Int) {
        allTasks = allTasks.filter { it.id != id }
        tasks = allTasks
    }

    fun filterByDone(done: Boolean) {
        tasks = allTasks.filter { it.done == done }
    }

    fun showAll() {
        tasks = allTasks
    }

    fun sortByDueDate() {
        allTasks = allTasks.sortedBy { it.dueDate }
        tasks = allTasks
    }
}
