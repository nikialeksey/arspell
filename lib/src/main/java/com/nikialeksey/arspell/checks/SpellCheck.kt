package com.nikialeksey.arspell.checks

import com.nikialeksey.arspell.Error

interface SpellCheck {
    fun check(): List<Error>
}