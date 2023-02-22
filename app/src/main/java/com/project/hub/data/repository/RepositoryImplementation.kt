package com.project.hub.data.repository

import com.project.hub.data.api.Api
import com.project.hub.domain.model.LoginModel
import com.project.hub.domain.model.ResposeModel
import com.project.hub.domain.repository.Repository
import retrofit2.Response
import javax.inject.Inject

class RepositoryImplementation @Inject constructor (
    private val api: Api
) : Repository  {

    override suspend fun login(loginData: LoginModel): Response<ResposeModel> {
        return api.login(loginData)
    }

}