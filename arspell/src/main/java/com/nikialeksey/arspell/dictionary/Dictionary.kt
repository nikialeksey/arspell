package com.nikialeksey.arspell.dictionary

import com.nikialeksey.arspell.words.Word

interface Dictionary {
    fun isCorrect(word: Word): Boolean
    fun suggest(word: Word): List<String>
    fun addIgnored(tokens: List<String>)

    object Dummy : Dictionary {
        override fun isCorrect(word: Word): Boolean {
            return true
        }

        override fun suggest(word: Word): List<String> {
            return emptyList()
        }

        override fun addIgnored(tokens: List<String>) {
            // ignore
        }

    }
}