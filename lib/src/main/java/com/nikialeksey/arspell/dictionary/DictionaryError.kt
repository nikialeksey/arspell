package com.nikialeksey.arspell.dictionary

import com.nikialeksey.arspell.Error

class DictionaryError(
    private val key: String,
    private val word: String,
    private val dictionary: Dictionary
) : Error {

    override fun key(): String {
        return key
    }

    override fun word(): String {
        return word
    }

    override fun suggests(): List<String> {
        return dictionary.suggest(word)
    }
}