/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.formatter

import com.intellij.openapi.project.Project
import com.intellij.psi.codeStyle.CodeStyleSettingsManager

object ProjectCodeStyleImporter {
    fun apply(project: Project, codeStyleStr: String?): Boolean {
        return when (codeStyleStr) {
            KotlinObsoleteCodeStyle.CODE_STYLE_SETTING -> {
                ProjectCodeStyleImporter.apply(project, KotlinObsoleteCodeStyle.INSTANCE)
                true
            }
            KotlinStyleGuideCodeStyle.CODE_STYLE_SETTING -> {
                ProjectCodeStyleImporter.apply(project, KotlinStyleGuideCodeStyle.INSTANCE)
                true
            }
            else -> false
        }
    }

    fun apply(project: Project, predefinedCodeStyle: KotlinPredefinedCodeStyle) {
        val settingsManager = CodeStyleSettingsManager.getInstance(project)

        settingsManager.USE_PER_PROJECT_SETTINGS = true
        settingsManager.PREFERRED_PROJECT_CODE_STYLE = null

        val projectSettings = settingsManager.currentSettings

        val kotlinDefaults = projectSettings.kotlinCodeStyleDefaults()
        if (kotlinDefaults != predefinedCodeStyle.codeStyleId) {
            predefinedCodeStyle.apply(projectSettings)
            settingsManager.mainProjectCodeStyle = projectSettings
        }
    }
}