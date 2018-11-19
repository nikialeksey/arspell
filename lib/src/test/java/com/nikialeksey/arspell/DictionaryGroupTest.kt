package com.nikialeksey.arspell

import com.atlascopco.hunspell.Hunspell
import org.junit.Assert
import org.junit.Test

class DictionaryGroupTest {
    @Test
    fun twoLanguage() {
        val errors = SimpleCheck(
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
                SimpleWords(
                    listOf(
                        SimpleWord("en", "Hello"),
                        SimpleWord("ru", "Привет")
                    )
                )
            )
        ).check()
        Assert.assertTrue(ErrorMessage(errors).asString(), errors.isEmpty())
    }
}