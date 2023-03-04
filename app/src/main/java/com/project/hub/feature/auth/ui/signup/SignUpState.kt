package com.project.hub.feature.auth.ui.signup

import com.project.hub.feature.auth.data.remote.entymodels.LoginResponse

sealed class SignUpState {

    object Loading: SignUpState()

    data class Success(val data: LoginResponse) : SignUpState()

    data class Error(val throwable: String) : SignUpState()

}
