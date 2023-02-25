package com.project.hub.core.domain

interface SessionManager {
    /**
     * Returns user token if user is authorized. Otherwise return NULL.
     */
    fun getUserTokenOrNull(): String?

    fun saveUserToken(token: String): Boolean

}