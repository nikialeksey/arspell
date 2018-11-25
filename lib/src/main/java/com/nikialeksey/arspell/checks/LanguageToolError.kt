package com.nikialeksey.arspell.checks

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
        return rule.sentence.text.substring(rule.fromPos, rule.toPos)
    }

    override fun suggests(): List<String> {
        return listOf(rule.message) + rule.suggestedReplacements
    }
}