package com.nikialeksey.arspell.md

import com.nikialeksey.hunspell.Hunspell
import com.nikialeksey.arpsell.md.MdStrings
import com.nikialeksey.arspell.DictionarySpell
import com.nikialeksey.arspell.ErrorMessage
import com.nikialeksey.arspell.GroupSpell
import com.nikialeksey.arspell.ProofToolSpell
import com.nikialeksey.arspell.hunspell.HunspellDictionary
import com.nikialeksey.arspell.langtool.LanguageToolProof
import org.junit.Assert
import org.junit.Test
import org.languagetool.JLanguageTool
import org.languagetool.language.AmericanEnglish
import java.io.File

class MdStringsTest {
    @Test
    fun simpleCheckMdString() {
        val errors = DictionarySpell(
            HunspellDictionary(
                Hunspell(
                    "./src/test/assets/en_US/index.dic",
                    "./src/test/assets/en_US/index.aff"
                )
            ),
            MdStrings("key", "### Hello world")
        ).check()
        Assert.assertTrue(ErrorMessage(errors).asString(), errors.isEmpty())
    }

    @Test
    fun checkMdFile() {
        val errors = DictionarySpell(
            HunspellDictionary(
                Hunspell(
                    "./src/test/assets/en_US/index.dic",
                    "./src/test/assets/en_US/index.aff"
                )
            ),
            MdStrings(File("./src/test/assets/sample.md"))
        ).check()
        Assert.assertTrue(ErrorMessage(errors).asString(), errors.isEmpty())
    }

    @Test
    fun checkReadMe() {
        val readmeStrings = MdStrings(File("../readme.md"))
        val ignoredTokens = listOf(
            "Arspell",
            "@todo",
            "#10",
            "30m",
            "codecov",
            "changelog",
            "gradle",
            "readme",
            "md",
            "xml",
            "GPLv3",
            "aarch64",
            "30m",
            "LanguageTool"
        )

        val dictionary = HunspellDictionary(
            Hunspell(
                "./src/test/assets/en_US/index.dic",
                "./src/test/assets/en_US/index.aff"
            )
        )
        dictionary.addIgnored(ignoredTokens)
        val dict = DictionarySpell(dictionary, readmeStrings)

        val proofTool = LanguageToolProof(JLanguageTool(AmericanEnglish()))
        proofTool.addIgnored(ignoredTokens)
        val proofToolSpell = ProofToolSpell(proofTool, readmeStrings)

        val errors = GroupSpell(listOf(dict, proofToolSpell)).check()

        Assert.assertTrue(ErrorMessage(errors).asString(), errors.isEmpty())
    }
}