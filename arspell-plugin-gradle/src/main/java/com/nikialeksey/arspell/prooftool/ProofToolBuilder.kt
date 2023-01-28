package com.nikialeksey.arspell.prooftool

import com.nikialeksey.arspell.langtool.LanguageToolProof
import com.nikialeksey.arspell.proofs.ProofTool
import com.nikialeksey.arspell.proofs.ProofToolGroup
import org.languagetool.JLanguageTool
import org.languagetool.language.English
import org.languagetool.language.Russian

open class ProofToolBuilder {

    private val tools: MutableList<ProofTool> = mutableListOf()

    fun en(): ProofToolBuilder {
        tools.add(LanguageToolProof(JLanguageTool(English())))
        return this
    }

    fun ru(): ProofToolBuilder {
        tools.add(LanguageToolProof(JLanguageTool(Russian())))
        return this
    }

    fun build(): ProofTool {
        return ProofToolGroup(tools)
    }
}