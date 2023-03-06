package com.project.hub.feature.auth.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
) : ViewModel() {

    // Flow
    private val _state = MutableSharedFlow<LoginState>(replay = 1)
    val state: SharedFlow<LoginState> = _state.asSharedFlow()

    fun login(email: String, password: String) = viewModelScope.launch(Dispatchers.IO) {

        _state.emit(LoginState.Loading)

        repository.login(email, password).onResult(
            onSuccess = {
                _state.emit(LoginState.Success(it.success))
            },
            onFailure = {
                _state.emit(LoginState.Error(it.cause.message.toString()))
            }
        )
    }

}