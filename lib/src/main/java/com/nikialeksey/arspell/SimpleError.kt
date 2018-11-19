package com.nikialeksey.arspell

class SimpleError(
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