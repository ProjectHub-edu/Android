package com.project.hub.application

import android.app.Application
import com.google.android.material.color.DynamicColors

class ProjectApplication : Application() {

    override fun onCreate() {
        DynamicColors.applyToActivitiesIfAvailable(this)
        super.onCreate()
    }

}