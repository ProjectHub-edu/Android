package com.project.hub.feature.profile.data.model

data class ProfileModel(
    val userName: String,
    val email: String,
    val avatarUrl: String,
    val description: String,
    val own_projects: List<ProjectModel>,
    val joined_projects: List<ProjectModel>,
)
