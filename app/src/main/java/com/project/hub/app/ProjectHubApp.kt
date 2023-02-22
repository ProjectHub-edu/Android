package com.project.hub.app

import android.app.Application
import com.google.android.material.color.DynamicColors

class ProjectHubApp : Application() {

    override fun onCreate() {
        DynamicColors.applyToActivitiesIfAvailable(this)
        super.onCreate()
    }

}