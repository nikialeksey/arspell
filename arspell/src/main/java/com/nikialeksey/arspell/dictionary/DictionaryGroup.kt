package com.nikialeksey.arspell.dictionary

import com.nikialeksey.arspell.words.Word

class DictionaryGroup(
    private val dictionaries: List<Dictionary>
) : Dictionary {

    override fun isCorrect(word: Word): Boolean {
        return dictionaries.any { it.isCorrect(word) }
    }

    override fun suggest(word: Word): List<String> {
        val result = mutableListOf<String>()

        for (dictionary in dictionaries) {
            result.addAll(dictionary.suggest(word))
        }

        return result
    }

    override fun addIgnored(tokens: List<String>) {
        dictionaries.forEach { it.addIgnored(tokens) }
    }
}