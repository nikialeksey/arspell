package com.nikialeksey.arspell

import com.atlascopco.hunspell.Hunspell
import com.nikialeksey.arspell.dictionary.DictionaryGroup
import com.nikialeksey.arspell.dictionary.HunspellDictionary
import com.nikialeksey.arspell.strings.*
import org.junit.Assert
import org.junit.Test
import java.io.File

class DictionarySpellTest {

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
                    AndroidStrings(File("./src/test/res/values/dictionary.xml")),
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
}