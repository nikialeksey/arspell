package com.nikialeksey.arspell.dictionary

import com.nikialeksey.arspell.words.Word

interface Dictionary {
    fun isCorrect(word: Word): Boolean
    fun suggest(word: Word): List<String>
}