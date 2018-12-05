package com.nikialeksey.arspell.checks

import com.atlascopco.hunspell.Hunspell
import com.nikialeksey.arspell.Error
import com.nikialeksey.arspell.dictionary.HunspellDictionary
import com.nikialeksey.arspell.strings.Strings

@Deprecated("Use DictionaryCheck instead", level = DeprecationLevel.WARNING)
class HunspellCheck(
    private val origin: SpellCheck
) : SpellCheck {

    constructor(hunspell: Hunspell, strings: Strings) : this(
        DictionaryCheck(
            HunspellDictionary(
                hunspell
            ),
            strings
        )
    )

    override fun check(): List<Error> {
        return origin.check()
    }
}