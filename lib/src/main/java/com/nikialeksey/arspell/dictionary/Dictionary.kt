package com.nikialeksey.arspell.dictionary

interface Dictionary {
    fun isCorrect(word: String): Boolean
    fun suggest(word: String): List<String>
}