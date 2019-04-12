package com.nikialeksey.arspell.md

import com.atlascopco.hunspell.Hunspell
import com.nikialeksey.arpsell.md.MdStrings
import com.nikialeksey.arspell.DictionarySpell
import com.nikialeksey.arspell.ErrorMessage
import com.nikialeksey.arspell.hunspell.HunspellDictionary
import org.junit.Assert
import org.junit.Test
import java.io.File

class MdStringsTest {
    @Test
    fun simpleCheckMdString() {
        val errors = DictionarySpell(
            HunspellDictionary(
                Hunspell(
                    "./src/test/assets/en_US/index.dic",
                    "./src/test/assets/en_US/index.aff"
                )
            ),
            MdStrings("key", "### Hello world")
        ).check()
        Assert.assertTrue(ErrorMessage(errors).asString(), errors.isEmpty())
    }

    @Test
    fun checkMdFile() {
        val errors = DictionarySpell(
            HunspellDictionary(
                Hunspell(
                    "./src/test/assets/en_US/index.dic",
                    "./src/test/assets/en_US/index.aff"
                )
            ),
            MdStrings(File("./src/test/assets/sample.md"))
        ).check()
        Assert.assertTrue(ErrorMessage(errors).asString(), errors.isEmpty())
    }

    @Test
    fun checkReadMe() {
        val errors = DictionarySpell(
            HunspellDictionary(
                Hunspell(
                    "./src/test/assets/en_US/index.dic",
                    "./src/test/assets/en_US/index.aff"
                )
            ),
            MdStrings(File("../readme.md"))
        ).check()
        Assert.assertTrue(ErrorMessage(errors).asString(), errors.isEmpty())
    }
}