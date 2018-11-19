package com.nikialeksey.arspell

interface Dictionary {
    fun isCorrect(word: String): Boolean
    fun suggest(word: String): List<String>
}