package com.nikialeksey.arspell.sentences

class SimpleSentences(
    private val key: String,
    private val sentences: String
) : Sentences {
    override fun asList(): List<Sentence> {
        return sentences.split("\n").map { SimpleSentence(key, it) }
    }
}