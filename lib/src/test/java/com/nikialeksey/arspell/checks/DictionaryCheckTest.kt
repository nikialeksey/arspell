package com.nikialeksey.arspell.checks

import com.atlascopco.hunspell.Hunspell
import com.nikialeksey.arspell.ErrorMessage
import com.nikialeksey.arspell.dictionary.DictionaryGroup
import com.nikialeksey.arspell.dictionary.HunspellDictionary
import com.nikialeksey.arspell.strings.SimpleString
import com.nikialeksey.arspell.strings.SimpleStrings
import org.junit.Assert
import org.junit.Test

class DictionaryCheckTest {
    @Test
    fun twoLanguage() {
        val errors = DictionaryCheck(
            DictionaryGroup(
                listOf(
                    HunspellDictionary(
                        Hunspell(
                            "./src/test/assets/en_US/index.dic",
                            "./src/test/assets/en_US/index.aff"
                        )
                    ),
                    HunspellDictionary(
                        Hunspell(
                            "./src/test/assets/ru/index.dic",
                            "./src/test/assets/ru/index.aff"
                        )
                    )
                )
            ),
            SimpleStrings(
                listOf(
                    SimpleString("en", "Hello"),
                    SimpleString("ru", "Привет")
                )
            )
        ).check()
        Assert.assertTrue(ErrorMessage(errors).asString(), errors.isEmpty())
    }

    @Test
    fun percentInText() {
        val errors = DictionaryCheck(
            HunspellDictionary(
                Hunspell(
                    "./src/test/assets/percents/index.dic",
                    "./src/test/assets/percents/index.aff"
                )
            ),
            SimpleStrings(
                listOf(
                    SimpleString("percent", "10%")
                )
            )
        ).check()
        Assert.assertTrue(ErrorMessage(errors).asString(), errors.isEmpty())
    }
}