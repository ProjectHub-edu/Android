package com.project.hub.feature.auth.ui.login

import com.project.hub.feature.auth.data.remote.entymodels.LoginResponse

sealed class LoginState {

    object Loading: LoginState()

    data class Success(val data: LoginResponse) : LoginState()

    data class Error(val throwable: String) : LoginState()

}
