package com.nikialeksey.arspell

import com.nikialeksey.arspell.dictionaries.RemoteHunspellDictionary
import com.nikialeksey.arspell.dictionary.Dictionary
import com.nikialeksey.arspell.dictionary.DictionaryGroup
import org.gradle.api.Action
import org.gradle.api.model.ObjectFactory
import java.io.File
import java.net.URL
import javax.inject.Inject

open class DictionaryBuilder @Inject constructor(
    private val objectFactory: ObjectFactory,
    private val cacheDir: File
) {
    private val dictionaries: MutableList<Dictionary> = mutableListOf()

    fun group(builderBlock: Action<DictionaryBuilder>): DictionaryBuilder {
        val builder = objectFactory.newInstance(DictionaryBuilder::class.java, cacheDir)
        builderBlock.execute(builder)
        dictionaries.add(builder.build())
        return this
    }

    fun en(): DictionaryBuilder {
        dictionaries.add(
            RemoteHunspellDictionary(
                URL("https://raw.githubusercontent.com/wooorm/dictionaries/main/dictionaries/en/index.dic"),
                URL("https://raw.githubusercontent.com/wooorm/dictionaries/main/dictionaries/en/index.aff"),
                File(cacheDir, "en")
            )
        )
        return this
    }

    fun ru(): DictionaryBuilder {
        dictionaries.add(
            RemoteHunspellDictionary(
                URL("https://raw.githubusercontent.com/wooorm/dictionaries/main/dictionaries/ru/index.dic"),
                URL("https://raw.githubusercontent.com/wooorm/dictionaries/main/dictionaries/ru/index.aff"),
                File(cacheDir, "ru")
            )
        )
        return this
    }

    fun build(): Dictionary {
        return if (dictionaries.isEmpty()) {
            Dictionary.Dummy
        } else {
            DictionaryGroup(dictionaries)
        }
    }
}