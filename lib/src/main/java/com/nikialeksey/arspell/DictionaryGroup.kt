package com.nikialeksey.arspell

class DictionaryGroup(
    private val dictionaries: List<Dictionary>
) : Dictionary {

    override fun isCorrect(word: String): Boolean {
        return dictionaries.any { it.isCorrect(word) }
    }

    override fun suggest(word: String): List<String> {
        val result = mutableListOf<String>()

        for (dictionary in dictionaries) {
            result.addAll(dictionary.suggest(word))
        }

        return result
    }
}