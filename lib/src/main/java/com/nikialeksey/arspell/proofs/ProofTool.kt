package com.nikialeksey.arspell.proofs

import com.nikialeksey.arspell.Error
import com.nikialeksey.arspell.strings.String

interface ProofTool {
    fun check(string: String): List<Error>
}