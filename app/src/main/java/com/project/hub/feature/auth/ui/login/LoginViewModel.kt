package com.project.hub.feature.auth.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.hub.core.domain.SessionManager
import com.project.hub.core.util.onResult
import com.project.hub.feature.auth.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    // Flow
    private val _exceptionState = MutableSharedFlow<LoginState>(replay = 1)
    val exceptionState: SharedFlow<LoginState> = _exceptionState.asSharedFlow()

    fun login(email: String, password: String) = viewModelScope.launch(Dispatchers.IO) {

        repository.login(email, password).onResult(
            onSuccess = {},
            onFailure = {
                _exceptionState.emit(LoginState.Error(it.cause.message.toString()))
            }
        )

        getToken()
    }

    fun getToken() {
        val token = sessionManager.getUserTokenOrNull()
        Log.d("TOKENCHECK", token.toString())

    }

}