package com.nikialeksey.arspell.strings

import com.nikialeksey.arspell.words.Word
import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Test

class IgnoreWordsStringsTest {
    @Test
    fun ignoreOneWordFromStrings() {
        val strings = IgnoreWordsStrings(
            SimpleStrings(
                listOf(
                    SimpleString("one", "hello ignoredWord"),
                    SimpleString("two", "ignoredWord world")
                )
            ),
            setOf("ignoredWord")
        ).asList()
        val words = mutableListOf<Word>()
        for (string in strings) {
            val sentences = string.sentences().asList()
            for (sentence in sentences) {
                words.addAll(sentence.words())
            }
        }
        Assert.assertThat(words.size, IsEqual.equalTo(2))
        Assert.assertThat(words[0].asString(), IsEqual.equalTo("hello"))
        Assert.assertThat(words[1].asString(), IsEqual.equalTo("world"))
    }
}