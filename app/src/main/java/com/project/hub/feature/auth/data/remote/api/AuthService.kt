package com.project.hub.feature.auth.data.remote.api

import com.project.hub.core.data.network.result.NetworkResult
import com.project.hub.feature.auth.data.remote.entymodels.LoginModel
import com.project.hub.feature.auth.data.remote.entymodels.ResponseModel
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

class LoginService @Inject constructor(
    @Named("loginRetrofit") private val retrofit: Retrofit
) {

    private val loginApi: LoginApi by lazy { retrofit.create(LoginApi::class.java) }

    suspend fun login(email: String, password: String): NetworkResult<ResponseModel> {
        val loginModel = LoginModel(email, password)
        return loginApi.login(loginModel)
    }

}