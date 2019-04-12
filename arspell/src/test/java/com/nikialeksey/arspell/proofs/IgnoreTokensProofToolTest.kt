package com.nikialeksey.arspell.proofs

import com.nikialeksey.arspell.strings.SimpleString
import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Test

class IgnoreTokensProofToolTest {
    @Test
    fun ignoreTokens() {
        val proofTool = IgnoreTokensProofTool(
            origin = ProofTool.Fake(setOf("one", "two")),
            tokens = listOf("asd")
        )
        proofTool.addIgnored(listOf("dsa"))
        Assert.assertThat(
            proofTool.check(SimpleString("key", "one two asd dsa")).size,
            IsEqual.equalTo(0)
        )
    }
}