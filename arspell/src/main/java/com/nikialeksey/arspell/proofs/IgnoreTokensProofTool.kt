package com.nikialeksey.arspell.proofs

import com.nikialeksey.arspell.Error

class IgnoreTokensProofTool(
    private val origin: ProofTool,
    private val tokens: List<String>
) : ProofTool {

    private val wasAddIgnored = mutableListOf<Unit>()

    override fun check(string: com.nikialeksey.arspell.strings.String): List<Error> {
        if (wasAddIgnored.isEmpty()) {
            origin.addIgnored(tokens)
            wasAddIgnored.add(Unit)
        }
        return origin.check(string)
    }

    override fun addIgnored(tokens: List<String>) {
        origin.addIgnored(tokens)
    }
}