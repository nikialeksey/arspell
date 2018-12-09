package com.nikialeksey.arspell.strings

class SimpleStrings(
    private val strings: List<String>
) : Strings {

    override fun asList(): List<String> {
        return strings
    }
}