package com.project.hub.feature.profile.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.hub.application.di.NetworkModule
import com.project.hub.core.data.network.result.NetworkResult
import com.project.hub.core.util.map
import com.project.hub.core.util.onResult
import com.project.hub.feature.profile.data.model.ProfileModel
import com.project.hub.feature.profile.data.ProfileRepository
import com.project.hub.feature.profile.data.model.ProjectModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileDetailsViewModel @Inject constructor(
    private val repo: ProfileRepository
) : ViewModel() {

    private val _state = MutableStateFlow<ProfileDataStates>(ProfileDataStates.Loading)
    val state: StateFlow<ProfileDataStates> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            repo.getProfileData(1).onResult(
                onSuccess = {
                    _state.value = ProfileDataStates.Success(it.success)
                },
                onFailure = {
                    _state.value = ProfileDataStates.Error("err")
                }
            )

        }
    }

}