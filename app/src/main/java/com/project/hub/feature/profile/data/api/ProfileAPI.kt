package com.project.hub.feature.profile.data.api

import com.project.hub.core.data.network.result.NetworkResult
import com.project.hub.feature.profile.data.model.ProfileModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileAPI {

    @GET("api/v1/user/{id}")
    suspend fun getProfileData(@Path("id") id: Int): NetworkResult<ProfileModel>

}