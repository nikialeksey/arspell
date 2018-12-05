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