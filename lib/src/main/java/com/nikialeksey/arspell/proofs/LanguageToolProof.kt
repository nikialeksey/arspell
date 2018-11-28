package com.nikialeksey.arspell.proofs

import com.nikialeksey.arspell.Error
import com.nikialeksey.arspell.checks.LanguageToolError
import com.nikialeksey.arspell.strings.String
import org.languagetool.JLanguageTool

class LanguageToolProof(
    private val languageTool: JLanguageTool
) : ProofTool {

    override fun check(string: String): List<Error> {
        return languageTool.check(string.asString()).map { LanguageToolError(string.key(), it) }
    }
}