package com.project.hub.feature.auth.ui.signup

import com.project.hub.feature.auth.data.remote.entymodels.LoginResponse
import com.project.hub.feature.auth.data.remote.entymodels.RegisterResponse

sealed class SignUpState {

    object Loading: SignUpState()

    data class Success(val response: Unit) : SignUpState()

    data class Error(val throwable: String) : SignUpState()

}
