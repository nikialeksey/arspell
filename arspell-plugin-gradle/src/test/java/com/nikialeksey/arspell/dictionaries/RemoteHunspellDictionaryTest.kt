package com.nikialeksey.arspell.dictionaries

import com.nikialeksey.arspell.dictionary.Dictionary
import com.nikialeksey.arspell.words.SimpleWord
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsEqual
import org.junit.BeforeClass
import org.junit.ClassRule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File
import java.net.URL

class RemoteHunspellDictionaryTest {
    @Test
    fun checkCorrectEnglishWord() {
        MatcherAssert.assertThat(
            en(cacheFolder).isCorrect(SimpleWord("key", "hello")),
            IsEqual.equalTo(true)
        )
    }

    @Test
    fun checkIncorrectEnglishWord() {
        MatcherAssert.assertThat(
            en(cacheFolder).isCorrect(SimpleWord("key", "helo")),
            IsEqual.equalTo(false)
        )
    }

    @Test
    fun checkCorrectRussianWord() {
        MatcherAssert.assertThat(
            ru(cacheFolder).isCorrect(SimpleWord("key", "привет")),
            IsEqual.equalTo(true)
        )
    }

    @Test
    fun checkIncorrectRussianWord() {
        MatcherAssert.assertThat(
            ru(cacheFolder).isCorrect(SimpleWord("key", "пивет")),
            IsEqual.equalTo(false)
        )
    }

    private fun en(cacheDir: File): Dictionary {
        return RemoteHunspellDictionary(
            URL("https://raw.githubusercontent.com/wooorm/dictionaries/main/dictionaries/en/index.dic"),
            URL("https://raw.githubusercontent.com/wooorm/dictionaries/main/dictionaries/en/index.aff"),
            File(cacheDir, "en")
        )
    }

    private fun ru(cacheDir: File): Dictionary {
        return RemoteHunspellDictionary(
            URL("https://raw.githubusercontent.com/wooorm/dictionaries/main/dictionaries/ru/index.dic"),
            URL("https://raw.githubusercontent.com/wooorm/dictionaries/main/dictionaries/ru/index.aff"),
            File(cacheDir, "ru")
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