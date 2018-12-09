package com.nikialeksey.arspell.dictionary

import com.nikialeksey.arspell.Error
import com.nikialeksey.arspell.words.Word

class DictionaryError(
    private val word: Word,
    private val dictionary: Dictionary
) : Error {

    override fun key(): String {
        return word.key()
    }

    override fun word(): String {
        return word.asString()
    }

    override fun suggests(): List<String> {
        return dictionary.suggest(word)
    }
}