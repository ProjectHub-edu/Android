package com.project.hub.core.data.network.api

import com.project.hub.core.data.network.result.NetworkResult
import com.project.hub.feature.auth.data.remote.entymodels.LoginModel
import com.project.hub.feature.auth.data.remote.entymodels.ResponseModel
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {

    @POST("/auth/login")
    suspend fun login(

        @Body
        loginData: LoginModel

//    ) : Response<ResponseModel>
    ) : NetworkResult<ResponseModel>
//    ) : Result<ResponseModel>
//    ) : ResultCallAdapter<ResponseModel>

}