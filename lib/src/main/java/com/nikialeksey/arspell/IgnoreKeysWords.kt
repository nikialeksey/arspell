package com.nikialeksey.arspell

class IgnoreKeysWords(
    private val origin: Words,
    private val keys: Collection<String>
) : Words {
    override fun iterator(): Iterator<Word> {
        return origin.filter { it.key() !in keys }.iterator()
    }
}