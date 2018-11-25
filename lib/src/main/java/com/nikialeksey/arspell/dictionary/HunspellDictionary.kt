package com.nikialeksey.arspell.dictionary

import com.atlascopco.hunspell.Hunspell

class HunspellDictionary(
    private val hunspell: Hunspell
) : Dictionary {

    override fun isCorrect(word: String): Boolean {
        return hunspell.isCorrect(word)
    }

    override fun suggest(word: String): List<String> {
        return hunspell.suggest(word)
    }
}