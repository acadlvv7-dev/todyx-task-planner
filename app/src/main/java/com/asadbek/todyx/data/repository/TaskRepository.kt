package com.asadbek.todyx.data.repository

import com.asadbek.todyx.data.locall.TaskDao
import com.asadbek.todyx.data.locall.TaskEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
){
    fun getAllTasks(): Flow<List<TaskEntity>> {
        return taskDao.getAllTasks()
    }

    suspend fun insertTask(task: TaskEntity){
        taskDao.insertTask(task)
    }

    suspend fun deleteTask(task: TaskEntity){
        taskDao.deleteTask(task)
    }

    suspend fun updateTask(task: TaskEntity){
        taskDao.updateTask(task)
    }
}
