package com.example.myapplication.data.repository

import com.example.myapplication.data.local.TaskDao
import com.example.myapplication.data.model.TaskEntity
import com.example.myapplication.model.Priority
import com.example.myapplication.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepository(private val taskDao: TaskDao) {
    val tasks: Flow<List<Task>> = taskDao.getAllTasks().map { list ->
        list.map { e ->
            Task(
                id = e.id,
                title = e.title,
                description = e.description,
                priority = runCatching { Priority.valueOf(e.priority) }.getOrElse { Priority.LOW },
                dueDate = e.dueDate,
                done = e.done
            )
        }
    }


    suspend fun addTask(task: Task) {
        taskDao.insert(
            TaskEntity(
                id = 0,
                title = task.title,
                description = task.description,
                priority = task.priority.name,
                dueDate = task.dueDate,
                done = task.done
            )
        )
    }

    suspend fun updateTask(task: Task) {
        taskDao.update(
            TaskEntity(
                id = task.id,
                title = task.title,
                description = task.description,
                priority = task.priority.name,
                dueDate = task.dueDate,
                done = task.done
            )
        )
    }

    suspend fun deleteTask(task: Task) {
        taskDao.delete(
            TaskEntity(
                id = task.id,
                title = task.title,
                description = task.description,
                priority = task.priority.name,
                dueDate = task.dueDate,
                done = task.done
            )
        )
    }

    suspend fun getTaskById(id: Int): Task? {
        val e = taskDao.getTaskById(id) ?: return null
        return Task(
            id = e.id,
            title = e.title,
            description = e.description,
            priority = runCatching { Priority.valueOf(e.priority) }.getOrElse { Priority.LOW },
            dueDate = e.dueDate,
            done = e.done
        )
    }
}
