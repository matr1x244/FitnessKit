package com.example.fitnesskit.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesskit.domain.RepositoryTraining
import com.example.fitnesskit.domain.data.TrainingModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository: RepositoryTraining) : ViewModel() {

    private val _reposListTraining = MutableSharedFlow<TrainingModel>()
    val reposListTraining: MutableSharedFlow<TrainingModel> = _reposListTraining

    private val _inProgress = MutableLiveData<Boolean>()
    val inProgress: LiveData<Boolean> = _inProgress

    fun onShowList() {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.d("@@@", "No success $throwable !!!")
            _inProgress.postValue(true)
        }
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val result = repository.listTraining()
            withContext(Dispatchers.Main) {
                _reposListTraining.emit(result)
                _inProgress.postValue(false)
            }
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}