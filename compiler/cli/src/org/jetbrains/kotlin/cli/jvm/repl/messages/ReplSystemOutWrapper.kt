/*
 * Copyright 2010-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.cli.jvm.repl.messages

import com.intellij.openapi.util.text.StringUtil
import com.intellij.util.LineSeparator
import java.io.PrintStream

val END_LINE: String = LineSeparator.getSystemLineSeparator().separatorString
val XML_PREAMBLE = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"

public class ReplSystemOutWrapper(private val ideMode: Boolean, standardOut: PrintStream) : PrintStream(standardOut, true) {
    private enum class EscapeType {
        INITIAL_PROMPT,
        HELP_PROMPT,
        USER_OUTPUT,
        REPL_RESULT,
        READLINE_START,
        READLINE_END,
        REPL_INCOMPLETE,
        COMPILE_ERROR,
        RUNTIME_ERROR,
        INTERNAL_ERROR
    }

    override fun print(x: Boolean) = printWithEscaping(x.toString())
    override fun print(x: Char) = printWithEscaping(x.toString())
    override fun print(x: Int) = printWithEscaping(x.toString())
    override fun print(x: Long) = printWithEscaping(x.toString())
    override fun print(x: Float) = printWithEscaping(x.toString())
    override fun print(x: Double) = printWithEscaping(x.toString())
    override fun print(x: String) = printWithEscaping(x)
    override fun print(x: Any?) = printWithEscaping(x.toString())

    private fun printlnWithEscaping(text: String, escapeType: EscapeType = EscapeType.USER_OUTPUT) = printWithEscaping("$text\n", escapeType)

    private fun printWithEscaping(text: String, escapeType: EscapeType = EscapeType.USER_OUTPUT) {
        if (ideMode)
            super.print("${xmlEscape(text, escapeType)}$END_LINE")
        else
            super.print(text)
    }

    private fun xmlEscape(s: String, escapeType: EscapeType): String {
        val singleLine = StringUtil.replace(s, SOURCE_CHARS, XML_REPLACEMENTS)
        return "$XML_PREAMBLE<output type=\"$escapeType\">${StringUtil.escapeXml(singleLine)}</output>"
    }

    fun printlnInit(x: String) = printlnWithEscaping(x, EscapeType.INITIAL_PROMPT)
    fun printlnHelp(x: String) = printlnWithEscaping(x, EscapeType.HELP_PROMPT)
    fun printlnResult(x: Any?) = printlnWithEscaping(x.toString(), EscapeType.REPL_RESULT)
    fun printlnReadLineStart() = printlnWithEscaping("", EscapeType.READLINE_START)
    fun printlnReadLineEnd() = printlnWithEscaping("", EscapeType.READLINE_END)
    fun printlnIncomplete() = printlnWithEscaping("", EscapeType.REPL_INCOMPLETE)
    fun printlnCompileError(x: String) = printlnWithEscaping(x, EscapeType.COMPILE_ERROR)
    fun printlnRuntimeError(x: String) = printlnWithEscaping(x, EscapeType.RUNTIME_ERROR)
    fun sendInternalErrorReport(x: String) = printlnWithEscaping(x, EscapeType.INTERNAL_ERROR)
}