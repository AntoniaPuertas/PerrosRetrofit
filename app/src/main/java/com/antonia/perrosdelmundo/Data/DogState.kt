package com.antonia.perrosdelmundo.Data

data class DogState(
    val dogs:List<DogsResponse> = emptyList(),
    val isLoading: Boolean = false
)
