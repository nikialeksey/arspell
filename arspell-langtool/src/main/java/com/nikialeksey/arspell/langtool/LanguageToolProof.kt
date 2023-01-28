package com.nikialeksey.arspell.langtool

import com.nikialeksey.arspell.Error
import com.nikialeksey.arspell.proofs.ProofTool
import com.nikialeksey.arspell.strings.String
import org.languagetool.JLanguageTool
import org.languagetool.rules.spelling.SpellingCheckRule

class LanguageToolProof(
    private val languageTool: JLanguageTool
) : ProofTool {

    override fun check(string: String): List<Error> {
        val text = string.asString()
        return languageTool.check(text).map {
            LanguageToolError(
                string.key(),
                text,
                it
            )
        }
    }

    override fun addIgnored(tokens: List<kotlin.String>) {
        languageTool
            .allActiveRules
            .filterIsInstance(SpellingCheckRule::class.java)
            .forEach {
                it.addIgnoreTokens(tokens)
            }
    }
}