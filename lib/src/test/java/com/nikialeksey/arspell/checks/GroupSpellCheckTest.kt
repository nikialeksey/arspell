package com.nikialeksey.arspell.checks

import com.atlascopco.hunspell.Hunspell
import com.nikialeksey.arspell.ErrorMessage
import com.nikialeksey.arspell.dictionary.DictionaryGroup
import com.nikialeksey.arspell.dictionary.HunspellDictionary
import com.nikialeksey.arspell.proofs.LanguageToolProof
import com.nikialeksey.arspell.strings.SimpleString
import com.nikialeksey.arspell.strings.SimpleStrings
import org.junit.Assert
import org.junit.Test
import org.languagetool.JLanguageTool
import org.languagetool.language.BritishEnglish

class GroupSpellCheckTest {
    @Test
    fun twoChecks() {
        val strings = SimpleStrings(
            listOf(
                SimpleString("1", "Hello"),
                SimpleString("2", "Sunday")
            )
        )
        val errors = GroupSpellCheck(
            listOf(
                DictionaryCheck(
                    DictionaryGroup(
                        listOf(
                            HunspellDictionary(
                                Hunspell(
                                    "./src/test/assets/en_US/index.dic",
                                    "./src/test/assets/en_US/index.aff"
                                )
                            )
                        )
                    ),
                    strings
                ),
                ProofToolCheck(
                    LanguageToolProof(
                        JLanguageTool(BritishEnglish())
                    ),
                    strings
                )
            )
        ).check()
        Assert.assertTrue(ErrorMessage(errors).asString(), errors.isEmpty())
    }
}