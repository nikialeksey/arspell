package com.nikialeksey.arspell

import com.atlascopco.hunspell.Hunspell
import com.nikialeksey.arspell.dictionary.HunspellDictionary
import com.nikialeksey.arspell.strings.AndroidStrings
import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Test
import java.io.File

class ErrorMessageTest {
    @Test
    fun wrappingEveryError() {
        val errors = DictionarySpell(
            HunspellDictionary(
                Hunspell(
                    "./src/test/assets/en_US/index.dic",
                    "./src/test/assets/en_US/index.aff"
                )
            ),
            AndroidStrings(File("./src/test/res/values/dictionary.xml"))
        ).check()
        val message = ErrorMessage(errors).asString()
        Assert.assertThat(message.count { it == '\n' }, IsEqual.equalTo(errors.size - 1))
    }
}