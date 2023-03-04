package com.project.hub.feature.auth.ui.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.hub.core.domain.SessionManager
import com.project.hub.core.util.onResult
import com.project.hub.feature.auth.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val sessionManager: SessionManager
) : ViewModel() {


    // Flow
    private val _exceptionState = MutableStateFlow<SignUpState>(SignUpState.Loading)
    val exceptionState: StateFlow<SignUpState> = _exceptionState.asStateFlow()

    fun register(
        email: String,
        password: String,
        username: String
    ) = viewModelScope.launch(Dispatchers.IO) {

        repository.register(email, password, username).onResult(
            onSuccess = {},
            onFailure = {
                _exceptionState.value = SignUpState.Error(it.cause.message.toString())
            }
        )
    }
}