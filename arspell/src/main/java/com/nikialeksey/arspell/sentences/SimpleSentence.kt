package com.nikialeksey.arspell.sentences

import com.nikialeksey.arspell.words.SimpleWord
import com.nikialeksey.arspell.words.SimpleWords
import com.nikialeksey.arspell.words.Word
import com.nikialeksey.arspell.words.Words
import java.util.*


class SimpleSentence(
    private val key: String,
    private val sentence: String,
    private val delim: String = " .,!?:-()_\\\"/…\u00A0"
) : Sentence {
    override fun key(): String {
        return key
    }

    override fun words(): Words {
        val result = mutableListOf<Word>()
        val words = StringTokenizer(sentence, delim)
        while (words.hasMoreTokens()) {
            val word = words.nextToken()
            result.add(SimpleWord(key, word))
        }
        return SimpleWords(result)
    }
}