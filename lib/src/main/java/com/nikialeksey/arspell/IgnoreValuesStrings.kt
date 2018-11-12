package com.nikialeksey.arspell

class IgnoreValuesStrings(
    private val origin: Strings,
    private val words: Collection<String>
) : Strings {
    override fun words(): Words {
        return IgnoreValuesWords(origin.words(), words)
    }
}