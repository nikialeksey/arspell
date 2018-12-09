package com.nikialeksey.arspell.words

import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Test

class IgnoreValuesWordsTest {
    @Test
    fun ignoredOneWord() {
        val words = IgnoreValuesWords(
            SimpleWords(
                listOf(
                    SimpleWord("key", "hello"),
                    SimpleWord("key", "asd")
                )
            ),
            setOf("asd")
        ).toList()
        Assert.assertThat(words.size, IsEqual.equalTo(1))
        Assert.assertThat(words[0].asString(), IsEqual.equalTo("hello"))
    }
}