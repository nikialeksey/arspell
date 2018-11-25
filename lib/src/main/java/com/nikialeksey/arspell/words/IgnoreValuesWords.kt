package com.nikialeksey.arspell.words

class IgnoreValuesWords(
    private val origin: Words,
    private val words: Collection<String>
) : Words {
    override fun iterator(): Iterator<Word> {
        return origin.filter { it.asString() !in words }.iterator()
    }
}