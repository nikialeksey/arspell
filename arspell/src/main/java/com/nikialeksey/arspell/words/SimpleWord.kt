package com.nikialeksey.arspell.words

class SimpleWord(
    private val key: String,
    private val word: String
) : Word {
    override fun key(): String {
        return key
    }

    override fun asString(): String {
        return word
    }

}