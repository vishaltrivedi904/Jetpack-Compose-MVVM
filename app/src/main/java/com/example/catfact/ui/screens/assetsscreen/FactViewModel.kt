package com.example.catfact.ui.screens.assetsscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catfact.data.api.NetworkManager
import com.example.catfact.data.api.NetworkResponse
import com.example.catfact.data.model.catfact.FactResponse
import com.example.catfact.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FactViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _assets = MutableLiveData<NetworkResponse<FactResponse>>()
    val assets: LiveData<NetworkResponse<FactResponse>> = _assets

    fun getAssetsById() {
        viewModelScope.launch {
            _assets.value = NetworkResponse.Loading
            val result = NetworkManager.safeApiCall {
                repository.getRandomFact()
            }
            _assets.value = result
        }
    }
}