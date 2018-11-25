package com.nikialeksey.arspell.checks

import com.nikialeksey.arspell.Error
import com.nikialeksey.arspell.dictionary.DictionaryError
import com.nikialeksey.arspell.dictionary.Dictionary
import com.nikialeksey.arspell.strings.Strings

class DictionaryCheck(
    private val dictionary: Dictionary,
    private val strings: Strings
) : SpellCheck {

    override fun check(): List<Error> {
        val result = mutableListOf<Error>()
        strings.asList()[0].sentences().asList()[0].words()
        for (string in strings.asList()) {
            for (sentence in string.sentences().asList()) {
                for (word in sentence.words()) {
                    val asString = word.asString()
                    if (!dictionary.isCorrect(asString)) {
                        result.add(DictionaryError(word.key(), asString, dictionary))
                    }
                }
            }
        }
        return result
    }
}