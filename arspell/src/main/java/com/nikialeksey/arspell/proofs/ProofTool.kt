package com.nikialeksey.arspell.proofs

import com.nikialeksey.arspell.Error
import com.nikialeksey.arspell.SimpleError
import com.nikialeksey.arspell.strings.String

interface ProofTool {
    fun check(string: String): List<Error>
    fun addIgnored(tokens: List<kotlin.String>)

    class Fake(
        private val knownTokens: Set<kotlin.String>
    ) : ProofTool {

        private val ignored = mutableSetOf<kotlin.String>()

        override fun check(string: String): List<Error> {
            val errors = mutableListOf<Error>()
            for (sentence in string.sentences().asList()) {
                for (word in sentence.words()) {
                    val wordString = word.asString()
                    if (wordString !in ignored && wordString !in knownTokens) {
                        errors.add(SimpleError(word))
                    }
                }
            }

            return errors
        }

        override fun addIgnored(tokens: List<kotlin.String>) {
            ignored.addAll(tokens)
        }

    }
}