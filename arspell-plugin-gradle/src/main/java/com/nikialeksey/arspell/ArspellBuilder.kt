package com.nikialeksey.arspell

import com.nikialeksey.arspell.strings.IgnoreKeysStrings
import com.nikialeksey.arspell.strings.IgnoreWordsStrings
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
    private val ignoredWords: MutableList<String> = mutableListOf()
    private val ignoredKeys: MutableList<String> = mutableListOf()
    private lateinit var strings: Strings

    fun dictionary(builderBlock: Action<DictionaryBuilder>): ArspellBuilder {
        builderBlock.execute(dictionaryBuilder)
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
        return DictionarySpell(
            dictionaryBuilder.build(),
            IgnoreWordsStrings(
                IgnoreKeysStrings(
                    strings,
                    ignoredKeys
                ),
                ignoredWords
            )
        )
    }
}