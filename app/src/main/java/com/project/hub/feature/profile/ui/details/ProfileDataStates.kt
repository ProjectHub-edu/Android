package com.project.hub.feature.profile.ui.details

import com.project.hub.feature.profile.data.model.ProfileModel

sealed class ProfileDataStates {
    object Loading : ProfileDataStates()

    data class Success(val data: ProfileModel) : ProfileDataStates()

    data class Error(val throwable: String) : ProfileDataStates()
}