package com.project.hub

import android.app.Application
import com.google.android.material.color.DynamicColors

class ProjectClassApp : Application() {

    override fun onCreate() {
        DynamicColors.applyToActivitiesIfAvailable(this)
        super.onCreate()
    }

}