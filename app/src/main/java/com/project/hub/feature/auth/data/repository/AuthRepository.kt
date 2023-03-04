package com.project.hub.feature.auth.data.repository

import com.project.hub.core.data.network.result.NetworkResult
import com.project.hub.core.domain.SessionManager
import com.project.hub.core.util.onFailure
import com.project.hub.feature.auth.data.remote.api.AuthService
import com.project.hub.core.util.Result
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authService: AuthService,
    private val sessionManager: SessionManager
) {

    suspend fun login(email: String, password: String): NetworkResult<Unit> {
        val loginResponse = authService.login(email, password)
            .onFailure { return it }

        // saveToken
        sessionManager.saveUserToken(loginResponse.token)

        return Result.Success(Unit)
    }

    suspend fun register(email: String, password: String, username: String): NetworkResult<Unit> {
        val registerResponse = authService.register(email, password, username)
            .onFailure { return it }

        // saveToken
        sessionManager.saveUserToken(registerResponse.token)

        return Result.Success(Unit)
    }



}