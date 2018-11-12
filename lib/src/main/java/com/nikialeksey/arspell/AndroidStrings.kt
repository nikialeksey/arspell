package com.nikialeksey.arspell

import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class AndroidStrings(private val file: File) : Strings {

    override fun words(): Words {
        return SimpleWords(
            SimpleSentences(
                BufferedReader(FileReader(file)).use { reader ->
                    var line = reader.readLine()
                    val localization = mutableMapOf<String, String>()
                    while (line != null) {
                        if (line.contains("<string name=\"")) {
                            localization.put(
                                line.split("\"")[1],
                                line.split("[<>]".toRegex())[2]
                            )
                        }

                        line = reader.readLine()
                    }
                    localization
                }
            )
        )
    }

}