package com.agaperra.repository.state


sealed class AppState {

    data class Success(val data: com.agaperra.repository.database.Word_Meaning) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()

}

