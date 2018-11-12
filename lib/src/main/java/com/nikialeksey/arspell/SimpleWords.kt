package com.nikialeksey.arspell

class SimpleWords : Words {

    private val words: List<Word>

    constructor(words: List<Word>) {
        this.words = words
    }

    constructor(sentences: Sentences) {
        this.words = mutableListOf()
        for (sentence in sentences) {
            this.words += sentence.words()
        }
    }

    override fun iterator(): Iterator<Word> {
        return words.iterator()
    }
}