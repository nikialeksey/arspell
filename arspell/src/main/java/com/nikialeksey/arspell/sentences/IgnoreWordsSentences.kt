package com.nikialeksey.arspell.sentences

class IgnoreWordsSentences(
    private val origin: Sentences,
    private val words: Collection<String>
) : Sentences {
    override fun asList(): List<Sentence> {
        return origin.asList().map { IgnoreWordsSentence(it, words) }
    }
}