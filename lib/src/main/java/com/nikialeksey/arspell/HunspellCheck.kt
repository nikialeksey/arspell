package com.nikialeksey.arspell

import com.atlascopco.hunspell.Hunspell

class HunspellCheck(
    private val origin: SpellCheck
) : SpellCheck {

    constructor(hunspell: Hunspell, strings: Strings) : this(SimpleCheck(HunspellDictionary(hunspell), strings))

    override fun check(): List<Error> {
        return origin.check()
    }
}