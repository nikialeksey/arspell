package com.nikialeksey.arspell

import com.nikialeksey.arspell.words.Word

class SimpleError(
    private val word: Word
) : Error {
    override fun key(): String {
        return word.key()
    }

    override fun word(): String {
        return word.asString()
    }

    override fun suggests(): List<String> {
        return emptyList()
    }
}