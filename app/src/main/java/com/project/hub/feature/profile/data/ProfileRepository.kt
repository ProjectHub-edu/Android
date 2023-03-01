package com.project.hub.feature.profile.data

import com.project.hub.core.data.network.result.NetworkResult
import com.project.hub.core.util.Result
import com.project.hub.core.util.onFailure
import com.project.hub.feature.profile.data.api.ProfileAPI
import com.project.hub.feature.profile.data.model.ProfileModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

class ProfileRepository @Inject constructor(
   @Named("profileRetrofit") val remote: ProfileAPI
) {

    suspend fun getProfileData(profileId: Int): NetworkResult<ProfileModel> {
        return TODO()
    }

    suspend fun updateProfileData(): NetworkResult<Unit> {
        val response = remote.getProfileData(1).onFailure {// getProfileData(1), 1 is plug
            return it
        }

//        profileDao.deleteUserGroups()
//        profileDao.insertUserGroups(response)

        return Result.Success(Unit)

    }

}