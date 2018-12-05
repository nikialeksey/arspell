package com.nikialeksey.arspell.checks

import com.nikialeksey.arspell.Error

class GroupSpellCheck(
    private val checks: List<SpellCheck>
) : SpellCheck {
    override fun check(): List<Error> {
        val result = mutableListOf<Error>()
        for (check in checks) {
            result.addAll(check.check())
        }
        return result
    }
}