package com.nikialeksey.arspell

import com.nikialeksey.hunspell.Hunspell
import com.nikialeksey.arspell.dictionary.DictionaryGroup
import com.nikialeksey.arspell.hunspell.HunspellDictionary
import com.nikialeksey.arspell.langtool.LanguageToolProof
import com.nikialeksey.arspell.strings.SimpleString
import com.nikialeksey.arspell.strings.SimpleStrings
import org.junit.Assert
import org.junit.Test
import org.languagetool.JLanguageTool
import org.languagetool.language.BritishEnglish

class GroupSpellTest {
    @Test
    fun twoChecks() {
        val strings = SimpleStrings(
            listOf(
                SimpleString("1", "Hello"),
                SimpleString("2", "Sunday")
            )
        )
        val errors = GroupSpell(
            listOf(
                DictionarySpell(
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
                ProofToolSpell(
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