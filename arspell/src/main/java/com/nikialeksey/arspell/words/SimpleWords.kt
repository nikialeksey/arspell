package com.nikialeksey.arspell.words

class SimpleWords : Words {

    private val words: List<Word>

    constructor(words: List<Word>) {
        this.words = words
    }

    override fun iterator(): Iterator<Word> {
        return words.iterator()
    }
}