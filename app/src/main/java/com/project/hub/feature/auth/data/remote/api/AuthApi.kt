package com.project.hub.feature.auth.data.remote.api

import com.project.hub.core.data.network.result.NetworkResult
import com.project.hub.feature.auth.data.remote.entymodels.LoginModel
import com.project.hub.feature.auth.data.remote.entymodels.LoginResponse
import com.project.hub.feature.auth.data.remote.entymodels.RegisterModel
import com.project.hub.feature.auth.data.remote.entymodels.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("user/login")
    suspend fun login(

        @Body
        loginData: LoginModel

    ) : NetworkResult<LoginResponse>

    @POST("user/new")
    suspend fun register(

        @Body
        registerModel: RegisterModel

    ) : NetworkResult<RegisterResponse>

}