package com.nikialeksey.arspell.md

import com.nikialeksey.arpsell.md.MdStrings
import com.nikialeksey.arspell.ErrorMessage
import com.nikialeksey.arspell.ProofToolSpell
import com.nikialeksey.arspell.langtool.LanguageToolProof
import org.junit.Assert
import org.junit.Test
import org.languagetool.JLanguageTool
import org.languagetool.language.BritishEnglish
import java.io.File

class MdStringsTest {
    @Test
    fun simpleCheckMdString() {
        val errors = ProofToolSpell(
            LanguageToolProof(
                JLanguageTool(
                    BritishEnglish()
                )
            ),
            MdStrings("key", "### Hello world")
        ).check()
        Assert.assertTrue(ErrorMessage(errors).asString(), errors.isEmpty())
    }

    @Test
    fun checkMdFile() {
        val errors = ProofToolSpell(
            LanguageToolProof(
                JLanguageTool(
                    BritishEnglish()
                )
            ),
            MdStrings(File("./src/test/assets/sample.md"))
        ).check()
        Assert.assertTrue(ErrorMessage(errors).asString(), errors.isEmpty())
    }
}