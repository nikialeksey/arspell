package com.nikialeksey.arspell.checks

import com.nikialeksey.arspell.ErrorMessage
import com.nikialeksey.arspell.proofs.LanguageToolProof
import com.nikialeksey.arspell.strings.AndroidStrings
import com.nikialeksey.arspell.strings.SimpleString
import com.nikialeksey.arspell.strings.SimpleStrings
import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Test
import org.languagetool.JLanguageTool
import org.languagetool.language.Russian
import java.io.File


class ProofToolCheckTest {
    @Test
    fun languageToolRu() {
        val errors = ProofToolCheck(
            LanguageToolProof(JLanguageTool(Russian())),
            SimpleStrings(
                listOf(SimpleString("city", "Ростов на Дону"))
            )
        ).check()
        Assert.assertThat(ErrorMessage(errors).asString(), errors.size, IsEqual.equalTo(1))
    }

    @Test
    fun languageToolLongString() {
        val languageTool = JLanguageTool(Russian())
        val errors = ProofToolCheck(
            LanguageToolProof(languageTool),
            AndroidStrings(File("./src/test/res/values/long.xml"))
        ).check()
        Assert.assertThat(ErrorMessage(errors).asString(), errors.size, IsEqual.equalTo(0))
    }

}