package com.nikialeksey.arspell

class ErrorMessage(
    private val errors: List<Error>
) : Message {
    override fun asString(): String {
        return if (errors.isEmpty()) {
            ""
        } else {
            errors.joinToString { "Key: ${it.key()}, word: ${it.word()}, suggests: ${it.suggests()}" }
        }
    }
}