package com.nikialeksey.arspell.check

import com.nikialeksey.arspell.ErrorMessage
import com.nikialeksey.arspell.checks.LanguageToolCheck
import com.nikialeksey.arspell.strings.SimpleString
import com.nikialeksey.arspell.strings.SimpleStrings
import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Test
import org.languagetool.JLanguageTool
import org.languagetool.language.Russian


class LanguageToolCheckTest {
    @Test
    fun languageToolRu() {
        val errors = LanguageToolCheck(
            JLanguageTool(Russian()),
            SimpleStrings(
                listOf(SimpleString("city", "Ростов на Дону"))
            )
        ).check()
        Assert.assertThat(ErrorMessage(errors).asString(), errors.size, IsEqual.equalTo(1))
    }
}