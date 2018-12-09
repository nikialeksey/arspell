package com.nikialeksey.arspell.langtool

import com.nikialeksey.arspell.Error
import org.languagetool.rules.RuleMatch

class LanguageToolError(
    private val key: String,
    private val rule: RuleMatch
) : Error {
    override fun key(): String {
        return key
    }

    override fun word(): String {
        val text = rule.sentence.text
        return text.substring(rule.fromPos, Math.min(rule.toPos, text.length - 1))
    }

    override fun suggests(): List<String> {
        return listOf(rule.message) + rule.suggestedReplacements
    }
}