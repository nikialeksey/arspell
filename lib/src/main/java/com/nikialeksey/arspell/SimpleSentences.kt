package com.nikialeksey.arspell

class SimpleSentences(private val sentences: Map<String, String>) : Sentences {
    override fun iterator(): Iterator<Sentence> {
        return sentences.entries.map { SimpleSentence(it.key, it.value) }.iterator()
    }
}