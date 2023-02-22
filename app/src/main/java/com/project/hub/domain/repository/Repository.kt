package com.project.hub.domain.repository

import com.project.hub.domain.model.LoginModel
import com.project.hub.domain.model.ResposeModel
import retrofit2.Response

interface Repository {

    suspend fun login(loginData: LoginModel): Response<ResposeModel>


}