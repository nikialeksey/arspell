package com.nikialeksey.arspell.dictionaries

import com.nikialeksey.arspell.words.SimpleWord
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsEqual
import org.junit.BeforeClass
import org.junit.ClassRule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File

class RuTest {

    @Test
    fun checkCorrectRussianWord() {
        MatcherAssert.assertThat(
            Ru(cacheFolder).isCorrect(SimpleWord("key", "привет")),
            IsEqual.equalTo(true)
        )
    }

    @Test
    fun checkIncorrectRussianWord() {
        MatcherAssert.assertThat(
            Ru(cacheFolder).isCorrect(SimpleWord("key", "пивет")),
            IsEqual.equalTo(false)
        )
    }

    companion object {
        @ClassRule
        @JvmField
        val temporaryFolder = TemporaryFolder()

        private lateinit var cacheFolder: File

        @BeforeClass
        @JvmStatic
        fun setupCacheFolder() {
            cacheFolder = temporaryFolder.newFolder()
        }
    }
}