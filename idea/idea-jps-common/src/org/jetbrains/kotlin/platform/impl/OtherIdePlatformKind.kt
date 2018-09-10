/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.platform.impl

import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments
import org.jetbrains.kotlin.cli.common.arguments.K2JSCompilerArguments
import org.jetbrains.kotlin.config.TargetPlatformVersion
import org.jetbrains.kotlin.js.resolve.JsPlatform
import org.jetbrains.kotlin.platform.IdePlatform
import org.jetbrains.kotlin.platform.IdePlatformKind

object OtherIdePlatformKind : IdePlatformKind<OtherIdePlatformKind>() {
    override fun platformByCompilerArguments(arguments: CommonCompilerArguments): IdePlatform<OtherIdePlatformKind, OtherCompilerArguments>? {
        return if (arguments is OtherCompilerArguments) Platform
        else null
    }

    override val compilerPlatform get() = JsPlatform

    override val platforms get() = listOf(Platform)
    override val defaultPlatform get() = Platform

    override val argumentsClass get() = K2JSCompilerArguments::class.java

    override val name get() = "Other"

    object Platform : IdePlatform<OtherIdePlatformKind, OtherCompilerArguments>() {
        override val kind get() = OtherIdePlatformKind
        override val version get() = TargetPlatformVersion.NoVersion
        override fun createArguments(init: OtherCompilerArguments.() -> Unit): OtherCompilerArguments = OtherCompilerArguments()
    }
}

class OtherCompilerArguments: CommonCompilerArguments()