package com.nikialeksey.arspell.strings

import com.vladsch.flexmark.ast.util.TextCollectingVisitor
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.profiles.pegdown.Extensions
import com.vladsch.flexmark.profiles.pegdown.PegdownOptionsAdapter
import org.cactoos.Text
import org.cactoos.text.TextOf
import java.io.*

class MdStrings(
    private val key: kotlin.String,
    private val text: Text,
    private val parser: Parser = Parser.builder(
        PegdownOptionsAdapter.flexmarkOptions(
            Extensions.ALL
        )
    ).build()
) : Strings {
    constructor(file: File) : this(file.name, TextOf(file))
    constructor(key: kotlin.String, strings: kotlin.String) : this(key, TextOf(strings))

    override fun asList(): List<String> {
        return listOf(
            SimpleString(
                key = key,
                value = TextCollectingVisitor().collectAndGetText(parser.parse(text.asString()))
            )
        )
    }
}