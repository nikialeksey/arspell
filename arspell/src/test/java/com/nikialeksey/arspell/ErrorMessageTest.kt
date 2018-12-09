package com.nikialeksey.arspell

import com.atlascopco.hunspell.Hunspell
import com.nikialeksey.arspell.hunspell.HunspellDictionary
import com.nikialeksey.arspell.strings.SimpleString
import com.nikialeksey.arspell.strings.SimpleStrings
import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Test

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
            SimpleStrings(
                listOf(SimpleString("unknown", "qweasd"))
            )
        ).check()
        val message = ErrorMessage(errors).asString()
        Assert.assertThat(message.count { it == '\n' }, IsEqual.equalTo(errors.size - 1))
    }
}