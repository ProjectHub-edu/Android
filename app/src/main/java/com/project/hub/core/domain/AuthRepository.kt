package com.project.hub.core.domain

import com.project.hub.feature.auth.data.model.LoginModel
import com.project.hub.core.util.Result

interface AuthRepository {

   suspend fun login(loginData: LoginModel): Result<Unit, Exception>


}