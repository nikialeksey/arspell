package com.nikialeksey.arspell

import com.atlascopco.hunspell.Hunspell

class HunspellError(
    private val key: String,
    private val word: String,
    private val hunspell: Hunspell
) : Error {

    override fun key(): String {
        return key
    }

    override fun word(): String {
        return word
    }

    override fun suggests(): List<String> {
        return hunspell.suggest(word)
    }
}