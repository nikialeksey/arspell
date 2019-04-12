package com.nikialeksey.arpsell.md

import com.nikialeksey.arspell.strings.SimpleString
import com.nikialeksey.arspell.strings.String
import com.nikialeksey.arspell.strings.Strings
import com.vladsch.flexmark.ast.FencedCodeBlock
import com.vladsch.flexmark.ast.util.TextCollectingVisitor
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.profiles.pegdown.Extensions
import com.vladsch.flexmark.profiles.pegdown.PegdownOptionsAdapter
import org.cactoos.Text
import org.cactoos.text.TextOf
import java.io.File

class MdStrings(
    private val key: kotlin.String,
    private val text: Text,
    private val parser: Parser = Parser.builder(
        PegdownOptionsAdapter.flexmarkOptions(
            Extensions.ALL
        )
    ).build(),
    private val visitor: TextCollectingVisitor = TextCollectingVisitor()
) : Strings {
    constructor(file: File) : this(file.name, TextOf(file))
    constructor(key: kotlin.String, strings: kotlin.String) : this(key, TextOf(strings))

    override fun asList(): List<String> {
        val document = parser.parse(text.asString())
        document.children.forEach {
            if (it is FencedCodeBlock) {
                it.unlink()
            }
        }

        return listOf(
            SimpleString(
                key = key,
                value = visitor.collectAndGetText(document).replace("\r", "")
            )
        )
    }
}