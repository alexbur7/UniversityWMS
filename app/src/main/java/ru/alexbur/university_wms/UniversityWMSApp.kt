package ru.alexbur.university_wms

import android.app.Application
import ru.alexbur.core.di.module.ContextModule
import ru.alexbur.core.domain.manager.CommonComponentManager
import ru.alexbur.university_wms.di.DaggerCommonComponent

class UniversityWMSApp : Application() {

    override fun onCreate() {
        super.onCreate()
        CommonComponentManager.component = DaggerCommonComponent.builder()
            .contextModule(ContextModule(applicationContext))
            .build()
    }
}