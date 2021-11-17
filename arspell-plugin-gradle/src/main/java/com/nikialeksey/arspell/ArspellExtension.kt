package com.nikialeksey.arspell

import com.nikialeksey.arpsell.md.MdStrings
import com.nikialeksey.arspell.android.AndroidStrings
import org.gradle.api.Action
import org.gradle.api.model.ObjectFactory
import java.io.File
import javax.inject.Inject

open class ArspellExtension @Inject constructor(
    private val objectFactory: ObjectFactory,
    private val cacheDir: File
) {

    private val arspells: MutableList<Arspell> = mutableListOf()

    fun md(
        file: File,
        builderBlock: Action<ArspellBuilder>
    ) {
        val arspellBuilder = objectFactory.newInstance(ArspellBuilder::class.java, cacheDir)
        builderBlock.execute(arspellBuilder)
        val arspell = arspellBuilder
            .strings(MdStrings(file))
            .build()
        arspells.add(arspell)
    }

    fun android(
        file: File,
        builderBlock: Action<ArspellBuilder>
    ) {
        val arspellBuilder = objectFactory.newInstance(ArspellBuilder::class.java, cacheDir)
        builderBlock.execute(arspellBuilder)
        val arspell = arspellBuilder
            .strings(AndroidStrings(file))
            .build()
        arspells.add(arspell)
    }

    fun arspell(): Arspell {
        return GroupSpell(arspells)
    }
}