package com.nikialeksey.arspell.strings

import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Test

class IgnoreKeysStringsTest {
    @Test
    fun ignoreOneString() {
        val strings = IgnoreKeysStrings(
            SimpleStrings(
                listOf(
                    SimpleString("ignored key", "asd"),
                    SimpleString("key", "hello")
                )
            ),
            setOf("ignored key")
        ).asList()
        Assert.assertThat(strings.size, IsEqual.equalTo(1))
        Assert.assertThat(strings[0].asString(), IsEqual.equalTo("hello"))
    }
}