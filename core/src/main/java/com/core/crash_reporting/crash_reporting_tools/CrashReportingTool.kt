package com.core.crash_reporting.crash_reporting_tools

import com.core.crash_reporting.CrashReportingToolIdentifier

interface CrashReportingTool {
    val identifier: CrashReportingToolIdentifier
    fun log(priority: Int, tag: String?, message: String, throwable: Throwable?)
    fun setCrashReportingKey(key: String, value: Any?)
}