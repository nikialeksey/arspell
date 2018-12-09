package com.nikialeksey.arspell.dictionary

import com.atlascopco.hunspell.Hunspell
import com.nikialeksey.arspell.DictionarySpell
import com.nikialeksey.arspell.ErrorMessage
import com.nikialeksey.arspell.hunspell.HunspellDictionary
import com.nikialeksey.arspell.strings.SimpleString
import com.nikialeksey.arspell.strings.SimpleStrings
import com.nikialeksey.arspell.words.SimpleWord
import org.hamcrest.core.IsCollectionContaining
import org.junit.Assert
import org.junit.Test

class DictionaryGroupTest {
    @Test
    fun twoLanguage() {
        val errors = DictionarySpell(
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
    fun suggestFromFewDictionaries() {
        val dictionary = DictionaryGroup(
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
        )
        Assert.assertThat(dictionary.suggest(SimpleWord("key", "woord")), IsCollectionContaining.hasItem("word"))
    }
}