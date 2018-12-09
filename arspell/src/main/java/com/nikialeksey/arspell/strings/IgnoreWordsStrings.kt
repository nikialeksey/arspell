package com.nikialeksey.arspell.strings

class IgnoreWordsStrings(
    private val origin: Strings,
    private val words: Collection<kotlin.String>
) : Strings {

    override fun asList(): List<String> {
        return origin.asList().map { IgnoreWordsString(it, words) }
    }
}