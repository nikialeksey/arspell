package com.nikialeksey.arspell.android

import com.nikialeksey.arspell.strings.SimpleString
import com.nikialeksey.arspell.strings.String
import com.nikialeksey.arspell.strings.Strings
import org.apache.commons.text.StringEscapeUtils
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory

class SimpleAndroidStrings(
    private val file: File
) : Strings {

    override fun asList(): List<String> {
        val fileDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file)
        val fileNodes = fileDocument.documentElement.childNodes
        val localization = mutableListOf<String>()
        for (i in 0 until fileNodes.length) {
            val node = fileNodes.item(i)
            if (node.nodeName == "string") {
                val name = node.attributes.getNamedItem("name").textContent
                val value = StringEscapeUtils.unescapeJava(node.textContent)
                localization.add(
                    SimpleString(name, value)
                )
            }
        }

        return localization
    }
}