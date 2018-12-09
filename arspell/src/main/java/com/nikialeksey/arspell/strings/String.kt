package com.nikialeksey.arspell.strings

import com.nikialeksey.arspell.sentences.Sentences


interface String {
    fun key(): kotlin.String
    fun sentences(): Sentences
    fun asString(): kotlin.String
}