package com.nikialeksey.arspell

interface Arspell {
    fun check(): List<Error>

    class Fake(
        private val errors: List<Error>
    ) : Arspell {

        constructor() : this(emptyList())

        override fun check(): List<Error> {
            return errors
        }
    }
}