package com.project.hub.presentation.fragments.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.hub.data.repository.RepositoryImplementation
import com.project.hub.domain.model.LoginModel
import com.project.hub.domain.model.ResposeModel
import com.project.hub.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    // Flow
    private val _loginData = MutableStateFlow<String>("Wait")
    val loginData: StateFlow<String> = _loginData.asStateFlow()

    fun login(loginData: LoginModel) = viewModelScope.launch(Dispatchers.IO) {
        val response = repository.login(loginData)
//        _loginData.value = response.body().toString()
        Log.d("ResponseBody", response.body().toString())
    }

}