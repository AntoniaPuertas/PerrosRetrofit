package com.antonia.perrosdelmundo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.antonia.perrosdelmundo.Data.DogState
import com.antonia.perrosdelmundo.Data.DogsResponse
import com.antonia.perrosdelmundo.Network.APIService
import kotlinx.coroutines.launch

class DogViewModel: ViewModel() {
    var state by mutableStateOf(DogState())
        private set
        var response:List<DogsResponse> by mutableStateOf(listOf())
        private set

    init{
        viewModelScope.launch {
            state = state.copy(
                isLoading = true
            )

            val apiService = APIService.getInstance()
            val dogResponse = apiService.getDogsByBreeds()
            response = listOf(dogResponse)

            state = state.copy(
                isLoading = false,
                dogs = response
            )
        }
    }
}