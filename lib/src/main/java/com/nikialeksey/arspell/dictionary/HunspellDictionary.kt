package com.nikialeksey.arspell.dictionary

import com.atlascopco.hunspell.Hunspell
import com.nikialeksey.arspell.words.Word

class HunspellDictionary(
    private val hunspell: Hunspell
) : Dictionary {

    override fun isCorrect(word: Word): Boolean {
        return hunspell.isCorrect(word.asString())
    }

    override fun suggest(word: Word): List<String> {
        return hunspell.suggest(word.asString())
    }
}