package com.nikialeksey.arspell

import com.nikialeksey.arspell.dictionary.DictionaryError
import com.nikialeksey.arspell.dictionary.Dictionary
import com.nikialeksey.arspell.strings.Strings

class DictionarySpell(
    private val dictionary: Dictionary,
    private val strings: Strings
) : Arspell {

    override fun check(): List<Error> {
        val result = mutableListOf<Error>()
        for (string in strings.asList()) {
            for (sentence in string.sentences().asList()) {
                for (word in sentence.words()) {
                    if (!dictionary.isCorrect(word)) {
                        result.add(DictionaryError(word, dictionary))
                    }
                }
            }
        }
        return result
    }
}