package com.nikialeksey.arspell.proofs

import com.nikialeksey.arspell.strings.SimpleString
import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Test

class IgnoreTokensProofToolTest {
    @Test
    fun asd() {
        val proofTool = IgnoreTokensProofTool(
            ProofTool.Fake(setOf("one", "two")), listOf("asd")
        )
        proofTool.addIgnored(listOf("dsa"))
        Assert.assertThat(
            proofTool.check(SimpleString("key", "one two asd dsa")).size,
            IsEqual.equalTo(0)
        )
    }
}