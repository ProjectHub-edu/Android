package com.project.hub.core.data.local

import com.project.hub.core.data.local.sharedpref.SharedPreferences
import com.project.hub.core.domain.SessionManager
import javax.inject.Inject

class SessionManagerImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : SessionManager {

    override fun getUserTokenOrNull(): String? {
        return sharedPreferences.getToken()
    }

    override fun saveUserToken(token: String): Boolean {
        sharedPreferences.saveToken(token)
        return !getUserTokenOrNull().isNullOrEmpty()

    }


}