package com.nikialeksey.arspell.android

import com.nikialeksey.arspell.strings.String
import com.nikialeksey.arspell.strings.Strings
import java.io.File

class AndroidStrings(
    private val origin: Strings
) : Strings {

    constructor(
        file: File
    ) : this(
        SkipReferencesStrings(
            SkipFormatSpecifiersStrings(
                SimpleAndroidStrings(file)
            )
        )
    )

    override fun asList(): List<String> {
        return origin.asList()
    }
}