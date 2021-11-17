package com.nikialeksey.arspell.dictionaries

import com.nikialeksey.arspell.dictionary.Dictionary
import com.nikialeksey.arspell.words.Word
import java.io.File
import java.net.URL

class Ru(
    private val origin: Dictionary
) : Dictionary {

    constructor(cache: File) : this(
        RemoteHunspellDictionary(
            URL("https://raw.githubusercontent.com/wooorm/dictionaries/main/dictionaries/ru/index.dic"),
            URL("https://raw.githubusercontent.com/wooorm/dictionaries/main/dictionaries/ru/index.aff"),
            File(cache, "ru")
        )
    )

    override fun isCorrect(word: Word): Boolean {
        return origin.isCorrect(word)
    }

    override fun suggest(word: Word): List<String> {
        return origin.suggest(word)
    }
}