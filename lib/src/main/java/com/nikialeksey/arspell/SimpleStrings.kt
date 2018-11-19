package com.nikialeksey.arspell

class SimpleStrings(
    private val words: Words
) : Strings {

    override fun words(): Words {
        return words
    }
}