package com.project.hub.feature.auth.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.hub.core.util.onResult
import com.project.hub.feature.auth.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val repository: AuthRepository,
) : ViewModel() {

    // Flow
    private val _state = MutableSharedFlow<SignUpState>(replay = 1)
    val state: SharedFlow<SignUpState> = _state.asSharedFlow()

    fun register(
        email: String,
        password: String,
        username: String
    ) = viewModelScope.launch(Dispatchers.IO) {

        _state.emit(SignUpState.Loading)

        repository.register(email, password, username).onResult(
            onSuccess = {
                _state.emit(SignUpState.Success(it.success))
            },
            onFailure = {
                _state.emit(SignUpState.Error(it.cause.message.toString()))
            }
        )
    }
}