package com.nikialeksey.arspell

import com.nikialeksey.arspell.prooftool.ProofToolBuilder
import com.nikialeksey.arspell.strings.IgnoreKeysStrings
import com.nikialeksey.arspell.strings.Strings
import org.gradle.api.Action
import org.gradle.api.model.ObjectFactory
import java.io.File
import javax.inject.Inject

open class ArspellBuilder @Inject constructor(
    private val objectFactory: ObjectFactory,
    private val cacheDir: File
) {
    private val dictionaryBuilder = objectFactory.newInstance(
        DictionaryBuilder::class.java,
        cacheDir
    )
    private val proofToolBuilder = objectFactory.newInstance(
        ProofToolBuilder::class.java
    )
    private val ignoredWords: MutableList<String> = mutableListOf()
    private val ignoredKeys: MutableList<String> = mutableListOf()
    private lateinit var strings: Strings

    fun dictionary(builderBlock: Action<DictionaryBuilder>): ArspellBuilder {
        builderBlock.execute(dictionaryBuilder)
        return this
    }

    fun proofTool(builderBlock: Action<ProofToolBuilder>): ArspellBuilder {
        builderBlock.execute(proofToolBuilder)
        return this
    }

    fun ignoreWords(ignoredWords: List<String>): ArspellBuilder {
        this.ignoredWords.addAll(ignoredWords)
        return this
    }

    fun ignoreKeys(ignoredKeys: List<String>): ArspellBuilder {
        this.ignoredKeys.addAll(ignoredKeys)
        return this
    }

    fun strings(strings: Strings): ArspellBuilder {
        this.strings = strings
        return this
    }

    fun build(): Arspell {
        val strings = IgnoreKeysStrings(
            strings,
            ignoredKeys
        )
        val dictionary = dictionaryBuilder.build()
        dictionary.addIgnored(ignoredWords)
        val dictionarySpell = DictionarySpell(dictionary, strings)

        val proofTool = proofToolBuilder.build()
        proofTool.addIgnored(ignoredWords)
        val proofToolSpell = ProofToolSpell(proofTool, strings)

        return GroupSpell(
            listOf(
                dictionarySpell,
                proofToolSpell
            )
        )
    }
}