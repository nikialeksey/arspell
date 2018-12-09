package com.nikialeksey.arspell.sentences

import com.nikialeksey.arspell.words.Words

interface Sentence {
    fun key(): String
    fun words(): Words
}