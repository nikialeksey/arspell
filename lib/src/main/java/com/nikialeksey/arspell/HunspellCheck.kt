package com.nikialeksey.arspell

import com.atlascopco.hunspell.Hunspell

class HunspellCheck(
    private val hunspell: Hunspell,
    private val strings: Strings
) : SpellCheck {

    override fun check(): List<Error> {
        val result = mutableListOf<Error>()

        for (word in strings.words()) {
            val asString = word.asString()
            if (!hunspell.isCorrect(asString)) {
                result.add(HunspellError(word.key(), asString, hunspell))
            }
        }

        return result
    }
}