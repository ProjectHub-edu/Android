package com.project.hub.core.domain

import com.project.hub.core.util.Result

interface AuthRepository {

   suspend fun login(): Result<Unit, Exception>


}