package com.nikialeksey.arspell.langtool

import com.nikialeksey.arspell.ErrorMessage
import com.nikialeksey.arspell.ProofToolSpell
import com.nikialeksey.arspell.proofs.IgnoreTokensProofTool
import com.nikialeksey.arspell.strings.SimpleString
import com.nikialeksey.arspell.strings.SimpleStrings
import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Test
import org.languagetool.JLanguageTool
import org.languagetool.language.Russian


class LanguageToolProofTest {
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
            SimpleStrings(
                listOf(
                    SimpleString(
                        "empty_vouchers_list",
                        "Пока чеков нет\nВоспользуйтесь сканером, чтобы загрузить свой первый чек и получить по нему кэшбэк"
                    )
                )
            )
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