package com.nikialeksey.arspell.android

import com.nikialeksey.arspell.strings.SimpleString
import com.nikialeksey.arspell.strings.String
import com.nikialeksey.arspell.strings.Strings

class SkipFormatSpecifiersStrings(
    private val origin: Strings,
    private val specifierPattern: Regex
) : Strings {

    constructor(
        origin: Strings
    ) : this(
        origin,
        // format specifier pattern, see Formatter.java docs
        "%(\\d+\\$)?([-#+ 0,(\\<]*)?(\\d+)?(\\.\\d+)?([tT])?([a-zA-Z%])".toRegex()
    )

    override fun asList(): List<String> {
        return origin.asList().map {
            val value = it.asString()
            SimpleString(it.key(), value.replace(specifierPattern, ""))
        }
    }
}