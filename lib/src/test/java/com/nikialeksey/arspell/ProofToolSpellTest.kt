package com.nikialeksey.arspell

import com.nikialeksey.arspell.proofs.IgnoreTokensProofTool
import com.nikialeksey.arspell.proofs.LanguageToolProof
import com.nikialeksey.arspell.strings.AndroidStrings
import com.nikialeksey.arspell.strings.SimpleString
import com.nikialeksey.arspell.strings.SimpleStrings
import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Test
import org.languagetool.JLanguageTool
import org.languagetool.language.Russian
import org.languagetool.rules.spelling.morfologik.MorfologikSpellerRule
import java.io.File


class ProofToolSpellTest {
    @Test
    fun languageToolRu() {
        val errors = ProofToolSpell(
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
        val errors = ProofToolSpell(
            LanguageToolProof(languageTool),
            AndroidStrings(File("./src/test/res/values/long.xml"))
        ).check()
        Assert.assertThat(ErrorMessage(errors).asString(), errors.size, IsEqual.equalTo(0))
    }

    @Test
    fun languageToolIgnoreWord() {
        val languageTool = JLanguageTool(Russian())
        val errors = ProofToolSpell(
            IgnoreTokensProofTool(
                LanguageToolProof(languageTool),
                listOf("фывфыв")
            ),
            SimpleStrings(
                listOf(SimpleString("key", "Привет, фывфыв"))
            )
        ).check()
        Assert.assertThat(ErrorMessage(errors).asString(), errors.size, IsEqual.equalTo(0))
    }

}