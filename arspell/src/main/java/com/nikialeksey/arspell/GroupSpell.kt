package com.nikialeksey.arspell

class GroupSpell(
    private val checks: List<Arspell>
) : Arspell {
    override fun check(): List<Error> {
        val result = mutableListOf<Error>()
        for (check in checks) {
            result.addAll(check.check())
        }
        return result
    }
}