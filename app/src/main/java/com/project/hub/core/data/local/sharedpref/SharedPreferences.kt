package com.project.hub.core.data.local.sharedpref

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferences @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        const val SHARED_PREFERENCES = "SHARED_PREFERENCES"
        const val TOKEN_KEY = "TOKEN_KEY"
    }

    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE)
    private val gson = Gson()

    fun saveToken(token: String) {
        sharedPreferences.edit()
            .putString(TOKEN_KEY, gson.toJson(token))
            .apply()
    }

    fun getToken(): String? {
        return gson.fromJson(
            sharedPreferences.getString(TOKEN_KEY, null),
            String::class.java
        )
    }

}