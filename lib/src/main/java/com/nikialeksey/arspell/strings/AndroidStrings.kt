package com.nikialeksey.arspell.strings

import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class AndroidStrings(private val file: File) : Strings {

    override fun asList(): List<String> {
        return BufferedReader(FileReader(file)).use { reader ->
            var line = reader.readLine()
            val localization = mutableListOf<String>()
            while (line != null) {
                if (line.contains("<string name=\"")) {
                    localization.add(
                        SimpleString(
                            line.split("\"")[1],
                            line.split("[<>]".toRegex())[2]
                        )
                    )
                }
                line = reader.readLine()
            }
            localization
        }
    }
}