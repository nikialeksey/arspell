package com.nikialeksey.arspell.proofs

import com.nikialeksey.arspell.Error
import com.nikialeksey.arspell.strings.String

class ProofToolGroup(
    private val tools: List<ProofTool>
) : ProofTool {

    override fun check(string: String): List<Error> {
        return tools.map { it.check(string) }.flatten()
    }

    override fun addIgnored(tokens: List<kotlin.String>) {
        tools.forEach { it.addIgnored(tokens) }
    }
}