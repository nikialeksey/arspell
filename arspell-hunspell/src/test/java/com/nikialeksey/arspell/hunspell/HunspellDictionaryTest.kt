package com.nikialeksey.arspell.hunspell

import com.atlascopco.hunspell.Hunspell
import com.nikialeksey.arspell.DictionarySpell
import com.nikialeksey.arspell.ErrorMessage
import com.nikialeksey.arspell.strings.IgnoreKeysStrings
import com.nikialeksey.arspell.strings.IgnoreWordsStrings
import com.nikialeksey.arspell.strings.SimpleString
import com.nikialeksey.arspell.strings.SimpleStrings
import org.junit.Assert
import org.junit.Test

class HunspellDictionaryTest {

    @Test
    fun spellDefault() {
        val errors = DictionarySpell(
            HunspellDictionary(
                Hunspell(
                    "./src/test/assets/en_US/index.dic",
                    "./src/test/assets/en_US/index.aff"
                )
            ),
            IgnoreWordsStrings(
                IgnoreKeysStrings(
                    SimpleStrings(
                        listOf(
                            SimpleString("ignored", "asdasd"),
                            SimpleString("ignored", "QWE - hello")
                        )
                    ),
                    setOf("ignored")
                ),
                setOf("QWE")
            )
        ).check()
        Assert.assertTrue(ErrorMessage(errors).asString(), errors.isEmpty())
    }

    @Test
    fun percentInText() {
        val errors = DictionarySpell(
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

    @Test
    fun percentInSimpleDictionary() {
        val errors = DictionarySpell(
            HunspellDictionary(
                Hunspell(
                    "./src/test/assets/en_US/index.dic",
                    "./src/test/assets/en_US/index.aff"
                )
            ),
            SimpleStrings(
                listOf(
                    SimpleString("percent", "10%")
                )
            )
        ).check()
        Assert.assertTrue(ErrorMessage(errors).asString(), errors.isNotEmpty())
    }
}