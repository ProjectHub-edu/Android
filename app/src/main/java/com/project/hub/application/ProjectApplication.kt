<<<<<<<< HEAD:app/src/main/java/com/project/hub/app/ProjectHubApp.kt
package com.project.hub.app
|||||||| 85ebab1:app/src/main/java/com/project/hub/ProjectClassApp.kt
package com.project.hub
========
package com.project.hub.application
>>>>>>>> origin/packaging:app/src/main/java/com/project/hub/application/ProjectApplication.kt

import android.app.Application
import com.google.android.material.color.DynamicColors
import dagger.hilt.android.HiltAndroidApp

<<<<<<<< HEAD:app/src/main/java/com/project/hub/app/ProjectHubApp.kt
@HiltAndroidApp
class ProjectHubApp : Application() {
|||||||| 85ebab1:app/src/main/java/com/project/hub/ProjectClassApp.kt
class ProjectClassApp : Application() {
========
class ProjectApplication : Application() {
>>>>>>>> origin/packaging:app/src/main/java/com/project/hub/application/ProjectApplication.kt

    override fun onCreate() {
        DynamicColors.applyToActivitiesIfAvailable(this)
        super.onCreate()
    }

}