package com.nikialeksey.arspell

import com.nikialeksey.arspell.dictionaries.En
import com.nikialeksey.arspell.dictionaries.Ru
import com.nikialeksey.arspell.dictionary.Dictionary
import com.nikialeksey.arspell.dictionary.DictionaryGroup
import org.gradle.api.Action
import org.gradle.api.model.ObjectFactory
import java.io.File
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
        dictionaries.add(En(cacheDir))
        return this
    }

    fun ru(): DictionaryBuilder {
        dictionaries.add(Ru(cacheDir))
        return this
    }

    fun build(): Dictionary {
        return DictionaryGroup(dictionaries)
    }
}