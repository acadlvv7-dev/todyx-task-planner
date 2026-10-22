package com.asadbek.todyx.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asadbek.todyx.data.locall.TaskEntity
import com.asadbek.todyx.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository
): ViewModel(){

    val tasks = repository.getAllTasks()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addTask(title: String, description: String = ""){
        viewModelScope.launch {
            val task = TaskEntity(
                title = title,
                description = description
            )
            repository.insertTask(task)
        }
    }

    fun deleteTask(task: TaskEntity){
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }

    fun toggleTask(task: TaskEntity){
        viewModelScope.launch {
            repository.updateTask(
                task.copy(isCompleted = !task.isCompleted)
            )
        }
    }

    fun updateTaskDetails(task: TaskEntity, title: String, description: String) {
        viewModelScope.launch {
            repository.updateTask(
                task.copy(title = title, description = description)
            )
        }
    }
}
