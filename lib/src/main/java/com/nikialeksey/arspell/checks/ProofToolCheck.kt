package com.nikialeksey.arspell.checks

import com.nikialeksey.arspell.Error
import com.nikialeksey.arspell.proofs.ProofTool
import com.nikialeksey.arspell.strings.Strings

class ProofToolCheck(
    private val proofTool: ProofTool,
    private val strings: Strings
) : SpellCheck {
    override fun check(): List<Error> {
        val errors = mutableListOf<Error>()

        for (string in strings.asList()) {
            errors.addAll(proofTool.check(string))
        }

        return errors
    }
}