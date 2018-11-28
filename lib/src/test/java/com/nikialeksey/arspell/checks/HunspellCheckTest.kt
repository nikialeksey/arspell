package com.nikialeksey.arspell.checks

import com.atlascopco.hunspell.Hunspell
import com.nikialeksey.arspell.ErrorMessage
import com.nikialeksey.arspell.strings.AndroidStrings
import com.nikialeksey.arspell.strings.IgnoreKeysStrings
import com.nikialeksey.arspell.strings.IgnoreWordsStrings
import org.junit.Assert
import org.junit.Test
import java.io.File

class HunspellCheckTest {
    @Test
    fun spellDefault() {
        val errors = HunspellCheck(
            Hunspell(
                "./src/test/assets/en_US/index.dic",
                "./src/test/assets/en_US/index.aff"
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
}