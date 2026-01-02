package com.heroku_app.application

import com.core.bases.base_application.BaseApplication
import com.core.crash_reporting.CrashReportingHandler
import com.core.crash_reporting.crash_reporting_tools.FirebaseCrashReportingTool
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class HerokuApplication : BaseApplication() {

    @Inject
    lateinit var firebaseCrashReportingTool: FirebaseCrashReportingTool


    override fun onCreate() {
        super.onCreate()
        initFirebaseApp()
    }

    private fun initFirebaseApp() {
        FirebaseApp.initializeApp(this)
    }

    override fun addCrashReportingTools(crashReportingHandler: CrashReportingHandler?) {
        if (this::firebaseCrashReportingTool.isInitialized) return
        crashReportingHandler?.registerCrashReportingTool(firebaseCrashReportingTool)
    }

    override fun getRemoteDebuggerPort() = 5656
}