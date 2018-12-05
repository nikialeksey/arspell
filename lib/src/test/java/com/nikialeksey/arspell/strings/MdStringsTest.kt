package com.nikialeksey.arspell.strings

import com.nikialeksey.arspell.ErrorMessage
import com.nikialeksey.arspell.checks.ProofToolCheck
import com.nikialeksey.arspell.proofs.LanguageToolProof
import org.junit.Assert
import org.junit.Test
import org.languagetool.JLanguageTool
import org.languagetool.language.BritishEnglish

class MdStringsTest {
    @Test
    fun asd() {
        val errors = ProofToolCheck(
            LanguageToolProof(
                JLanguageTool(
                    BritishEnglish()
                )
            ),
            MdStrings("key", "Hello, **dog**!")
        ).check()
        Assert.assertTrue(ErrorMessage(errors).asString(), errors.isEmpty())
    }
}