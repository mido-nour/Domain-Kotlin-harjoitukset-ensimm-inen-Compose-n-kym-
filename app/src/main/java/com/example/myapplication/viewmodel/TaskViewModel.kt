package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Task
import com.example.myapplication.model.mockTasks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TaskViewModel : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    private var allTasks: List<Task> = emptyList()

    init {
        allTasks = mockTasks
        _tasks.value = allTasks
    }

    fun addTask(task: Task) {
        allTasks = allTasks + task
        _tasks.value = allTasks
    }

    fun toggleDone(id: Int) {
        allTasks = allTasks.map { task ->
            if (task.id == id) task.copy(done = !task.done) else task
        }
        _tasks.value = allTasks
    }

    fun removeTask(id: Int) {
        allTasks = allTasks.filter { it.id != id }
        _tasks.value = allTasks
    }

    fun filterByDone(done: Boolean) {
        _tasks.value = allTasks.filter { it.done == done }
    }

    fun showAll() {
        _tasks.value = allTasks
    }

    fun sortByDueDate() {
        allTasks = allTasks.sortedBy { it.dueDate }
        _tasks.value = allTasks
    }

    fun updateTask(updated: Task) {
        allTasks = allTasks.map { if (it.id == updated.id) updated else it }
        _tasks.value = allTasks
    }
}
