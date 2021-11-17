package com.nikialeksey.arspell.dictionaries

import com.atlascopco.hunspell.Hunspell
import com.nikialeksey.arspell.dictionary.Dictionary
import com.nikialeksey.arspell.hunspell.HunspellDictionary
import com.nikialeksey.arspell.words.Word
import org.apache.commons.io.FileUtils
import org.cactoos.Scalar
import org.cactoos.scalar.SolidScalar
import java.io.File
import java.net.URL

class RemoteHunspellDictionary(
    private val origin: Scalar<Dictionary>
) : Dictionary {

    constructor(
        dicUrl: URL,
        affUrl: URL,
        cacheDir: File
    ) : this(SolidScalar {
        val dicFile = File(cacheDir, "index.dic")
        val affFile = File(cacheDir, "index.aff")
        if (!dicFile.exists() || !affFile.exists()) {
            FileUtils.copyURLToFile(dicUrl, dicFile);
            FileUtils.copyURLToFile(affUrl, affFile);
        }
        HunspellDictionary(
            Hunspell(
                dicFile.canonicalPath,
                affFile.canonicalPath
            )
        )
    })

    override fun isCorrect(word: Word): Boolean {
        return origin.value().isCorrect(word)
    }

    override fun suggest(word: Word): List<String> {
        return origin.value().suggest(word)
    }
}