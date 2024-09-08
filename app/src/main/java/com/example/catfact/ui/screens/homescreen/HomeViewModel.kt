package com.example.catfact.ui.screens.homescreen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.catfact.data.model.catbread.Data
import com.example.catfact.data.datasource.BreedsPagingSource
import com.example.catfact.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private var _breadResponse: MutableStateFlow<PagingData<Data>> =
        MutableStateFlow(PagingData.empty())
    var breadResponse = _breadResponse.asStateFlow()

    fun getBreeds() {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(
                    pageSize = 10,
                    enablePlaceholders = true
                ),
                pagingSourceFactory = { BreedsPagingSource(repository) } // Create a new instance here
            ).flow.cachedIn(viewModelScope).collect {
                _breadResponse.value = it
            }
        }
    }
}