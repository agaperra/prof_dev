package com.agaperra.professionaldevelopment.data.state

import com.agaperra.professionaldevelopment.data.database.Word_Meaning


sealed class AppState {

    data class Success(val data: Word_Meaning) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()

}

