package com.example.fitnesskit.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesskit.domain.RepositoryTraining
import com.example.fitnesskit.domain.model.Lesson
import com.example.fitnesskit.domain.model.TrainingModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository: RepositoryTraining) : ViewModel() {

    private val _repos = MutableSharedFlow<TrainingModel>()
    val repos: MutableSharedFlow<TrainingModel> = _repos

    fun onShowList() {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.d("@@@", "No success $throwable !!!")
        }
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val result = repository.listTraining()
            withContext(Dispatchers.Main) {
                _repos.emit(result)
            }
        }
    }
}