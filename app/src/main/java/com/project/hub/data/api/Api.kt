package com.project.hub.data.api

import com.project.hub.domain.model.LoginModel
import com.project.hub.domain.model.ResposeModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {

    @POST("/auth/login")
    suspend fun login(

        @Body
        loginData: LoginModel

    ) : Response<ResposeModel>

}