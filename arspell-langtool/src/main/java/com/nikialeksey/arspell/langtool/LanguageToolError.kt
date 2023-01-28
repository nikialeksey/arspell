package com.nikialeksey.arspell.langtool

import com.nikialeksey.arspell.Error
import org.languagetool.rules.RuleMatch

class LanguageToolError(
    private val key: String,
    private val text: String,
    private val rule: RuleMatch
) : Error {
    override fun key(): String {
        return key
    }

    override fun word(): String {
        val startIndex = Math.min(Math.max(rule.fromPos, 0), text.length - 1)
        val endIndex = Math.min(Math.max(rule.toPos, 0) + 1, text.length)
        return text.substring(startIndex, endIndex)
    }

    override fun suggests(): List<String> {
        return listOf(rule.message) + rule.suggestedReplacements
    }
}