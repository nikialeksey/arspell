package com.nikialeksey.arspell

class SimpleCheck(
    private val dictionary: Dictionary,
    private val strings: Strings
) : SpellCheck {

    override fun check(): List<Error> {
        val result = mutableListOf<Error>()

        for (word in strings.words()) {
            val asString = word.asString()
            if (!dictionary.isCorrect(asString)) {
                result.add(SimpleError(word.key(), asString, dictionary))
            }
        }

        return result
    }
}