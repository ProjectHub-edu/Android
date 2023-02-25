package com.project.hub.feature.auth.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.hub.core.data.local.SessionManagerImpl
import com.project.hub.core.domain.SessionManager
//import com.project.hub.core.domain.AuthRepository
import com.project.hub.feature.auth.data.remote.entymodels.LoginModel
import com.project.hub.feature.auth.data.repository.AuthRepository
//import com.project.hub.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel @Inject constructor(
    private val repository: AuthRepository,
//    private val sessionManager: SessionManager
    private val sessionManager: SessionManager
) : ViewModel() {

    // Flow
//    private val _loginData = MutableStateFlow<String>("Wait")
//    val loginData: StateFlow<String> = _loginData.asStateFlow()

    fun login(email: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.login(email, password)

        getToken()
    }

    fun getToken() {
        val token = sessionManager.getUserTokenOrNull()
        Log.d("TOKENCHECK", token.toString())

    }

}