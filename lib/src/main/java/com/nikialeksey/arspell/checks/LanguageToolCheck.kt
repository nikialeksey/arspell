package com.nikialeksey.arspell.checks

import com.nikialeksey.arspell.Error
import com.nikialeksey.arspell.strings.Strings
import org.languagetool.JLanguageTool

class LanguageToolCheck(
    private val languageTool: JLanguageTool,
    private val strings: Strings
) : SpellCheck {
    override fun check(): List<Error> {
        val errors = mutableListOf<Error>()

        for (string in strings.asList()) {
            val rules = languageTool.check(string.asString())
            for (rule in rules) {
                errors.add(LanguageToolError(string.key(), rule))
            }
        }

        return errors
    }
}