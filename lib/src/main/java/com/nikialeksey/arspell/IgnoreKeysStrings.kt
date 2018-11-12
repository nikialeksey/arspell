package com.nikialeksey.arspell

class IgnoreKeysStrings(
    private val origin: Strings,
    private val keys: Collection<String>
) : Strings {
    override fun words(): Words {
        return IgnoreKeysWords(origin.words(), keys)
    }
}