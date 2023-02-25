package com.project.hub.feature.auth.data.repository

import android.util.Log
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
        val saveResponse = sessionManager.saveUserToken(loginResponse.token)

        Log.d("ANSWR1111", loginResponse.toString())
        Log.d("ANSWR1111", saveResponse.toString())

        return Result.Success(Unit)
    }
}