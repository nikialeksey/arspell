package com.nikialeksey.arspell.sentences

import com.nikialeksey.arspell.words.IgnoreValuesWords
import com.nikialeksey.arspell.words.Words

class IgnoreWordsSentence(
    private val origin: Sentence,
    private val words: Collection<String>
) : Sentence {
    override fun key(): String {
        return origin.key()
    }

    override fun words(): Words {
        return IgnoreValuesWords(origin.words(), words)
    }

}