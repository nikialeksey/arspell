package com.nikialeksey.arspell.strings

class IgnoreKeysStrings(
    private val origin: Strings,
    private val keys: Collection<kotlin.String>
) : Strings {

    override fun asList(): List<String> {
        return origin.asList().filter { it.key() !in keys }
    }
}