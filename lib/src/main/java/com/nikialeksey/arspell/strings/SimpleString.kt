package com.nikialeksey.arspell.strings

import com.nikialeksey.arspell.sentences.Sentences
import com.nikialeksey.arspell.sentences.SimpleSentences

class SimpleString(
    private val key: kotlin.String,
    private val value: kotlin.String
) : String {

    override fun key(): kotlin.String {
        return key
    }

    override fun sentences(): Sentences {
        return SimpleSentences(key, value)
    }

    override fun asString(): kotlin.String {
        return value
    }
}