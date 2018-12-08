package com.nikialeksey.arspell

import com.nikialeksey.arspell.proofs.ProofTool
import com.nikialeksey.arspell.strings.Strings

class ProofToolSpell(
    private val proofTool: ProofTool,
    private val strings: Strings
) : Arspell {
    override fun check(): List<Error> {
        val errors = mutableListOf<Error>()

        for (string in strings.asList()) {
            errors.addAll(proofTool.check(string))
        }

        return errors
    }
}