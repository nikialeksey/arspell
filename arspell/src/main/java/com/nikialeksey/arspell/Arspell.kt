package com.nikialeksey.arspell

import com.nikialeksey.arspell.Error

interface Arspell {
    fun check(): List<Error>
}