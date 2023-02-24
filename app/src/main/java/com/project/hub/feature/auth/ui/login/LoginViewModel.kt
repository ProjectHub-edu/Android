package com.project.hub.feature.auth.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.hub.core.domain.AuthRepository
import com.project.hub.feature.auth.data.model.LoginModel
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
//    private val repository: Repository
    private val repository: AuthRepository
) : ViewModel() {

    // Flow
    private val _loginData = MutableStateFlow<String>("Wait")
    val loginData: StateFlow<String> = _loginData.asStateFlow()

    fun login(loginData: LoginModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.login(loginData)
//        _loginData.value = response.body().toString()
//        Log.d("ResponseBody", response.body().toString())
    }

}