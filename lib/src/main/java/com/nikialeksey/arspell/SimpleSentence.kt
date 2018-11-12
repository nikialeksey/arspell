package com.nikialeksey.arspell

import java.util.*


class SimpleSentence(
    private val key: String,
    private val sentences: String,
    private val delim: String = " .,!?:-()_\\\"/…"
) : Sentence {
    override fun key(): String {
        return key
    }

    override fun words(): Words {
        val result = mutableListOf<Word>()
        for (sentence in sentences.split("\\n")) {
            val words = StringTokenizer(sentence, delim)
            while (words.hasMoreTokens()) {
                val word = words.nextToken()
                result.add(SimpleWord(key, word))
            }
        }
        return SimpleWords(result)
    }
}