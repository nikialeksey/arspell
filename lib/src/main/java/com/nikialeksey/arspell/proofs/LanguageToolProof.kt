package com.nikialeksey.arspell.proofs

import com.nikialeksey.arspell.Error
import com.nikialeksey.arspell.strings.String
import org.languagetool.JLanguageTool
import org.languagetool.rules.spelling.morfologik.MorfologikSpellerRule

class LanguageToolProof(
    private val languageTool: JLanguageTool
) : ProofTool {

    override fun check(string: String): List<Error> {
        return languageTool.check(string.asString()).map {
            LanguageToolError(
                string.key(),
                it
            )
        }
    }

    override fun addIgnored(tokens: List<kotlin.String>) {
        val rule = languageTool.allActiveRules.find {
            it is MorfologikSpellerRule
        } as MorfologikSpellerRule?
        rule?.addIgnoreTokens(tokens)
    }
}