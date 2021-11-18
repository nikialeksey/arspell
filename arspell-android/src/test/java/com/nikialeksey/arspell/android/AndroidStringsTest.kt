package com.nikialeksey.arspell.android

import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Test
import java.io.File

class AndroidStringsTest {
    @Test
    fun dictionaryTest() {
        val strings = AndroidStrings(File("./src/test/res/values/dictionary.xml")).asList()
        Assert.assertThat(strings.size, IsEqual.equalTo(1))
        Assert.assertThat(strings[0].asString(), IsEqual.equalTo("Android resources spell\ntesting library "))
    }
}