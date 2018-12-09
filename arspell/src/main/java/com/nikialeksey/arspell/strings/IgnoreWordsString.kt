package com.nikialeksey.arspell.strings

import com.nikialeksey.arspell.sentences.IgnoreWordsSentences
import com.nikialeksey.arspell.sentences.Sentences

class IgnoreWordsString(
    private val origin: String,
    private val ignored: Collection<kotlin.String>
) : String {
    override fun key(): kotlin.String {
        return origin.key()
    }

    override fun sentences(): Sentences {
        return IgnoreWordsSentences(origin.sentences(), ignored)
    }

    override fun asString(): kotlin.String {
        return origin.asString()
    }
}