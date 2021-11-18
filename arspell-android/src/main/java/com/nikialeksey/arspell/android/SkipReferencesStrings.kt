package com.nikialeksey.arspell.android

import com.nikialeksey.arspell.strings.String
import com.nikialeksey.arspell.strings.Strings
import java.util.regex.Pattern

class SkipReferencesStrings(
    private val origin: Strings,
    private val referencePattern: Pattern
) : Strings {

    constructor(
        origin: Strings
    ) : this(
        origin,
        Pattern.compile("@string/[a-zA-Z_][a-zA-Z_0-9]*")
    )

    override fun asList(): List<String> {
        return origin.asList().filter {
            !referencePattern.matcher(it.asString()).matches()
        }
    }
}