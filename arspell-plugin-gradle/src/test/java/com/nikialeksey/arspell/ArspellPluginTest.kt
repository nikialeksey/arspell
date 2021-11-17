package com.nikialeksey.arspell

import org.gradle.api.GradleException
import org.gradle.testfixtures.ProjectBuilder
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsNull
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class ArspellPluginTest {

    @Rule
    @JvmField
    val tmpFolder = TemporaryFolder()

    @Test
    fun configurePluginWithEmptyExtension() {
        val projectFolder = tmpFolder.newFolder()
        val project = ProjectBuilder.builder()
            .withProjectDir(projectFolder)
            .build()
        project.pluginManager.apply("com.nikialeksey.arspell")
        project.evaluationDependsOn(":")

        MatcherAssert.assertThat(
            project.extensions.getByType(ArspellExtension::class.java),
            IsNull.notNullValue()
        )
        MatcherAssert.assertThat(
            project.tasks.getByName("arspell"),
            IsNull.notNullValue()
        )
    }

    @Test
    fun configurePluginWithCorrectMdFile() {
        val mdFile = tmpFolder.newFile()
        mdFile.writeText("Hello, world!\nПривет, мир!\n")
        val projectFolder = tmpFolder.newFolder()
        val project = ProjectBuilder.builder()
            .withProjectDir(projectFolder)
            .build()
        project.pluginManager.apply("com.nikialeksey.arspell")
        val extension = project.extensions.getByType(ArspellExtension::class.java)
        extension.md(mdFile) { builder ->
            builder.dictionary { dictBuilder ->
                dictBuilder.en()
                dictBuilder.ru()
            }
        }
        project.evaluationDependsOn(":")
        val arspellTask = project.tasks.getByName("arspell") as ArspellTask

        try {
            arspellTask.check()
            // green
        } catch (e: GradleException) {
            Assert.fail(e.message)
        }
    }

    @Test
    fun configurePluginWithIncorrectMdFile() {
        val mdFile = tmpFolder.newFile()
        mdFile.writeText("Hello, worllld!\n")
        val projectFolder = tmpFolder.newFolder()
        val project = ProjectBuilder.builder()
            .withProjectDir(projectFolder)
            .build()
        project.pluginManager.apply("com.nikialeksey.arspell")
        val extension = project.extensions.getByType(ArspellExtension::class.java)
        extension.md(mdFile) { builder ->
            builder.dictionary { dictBuilder ->
                dictBuilder.en()
                dictBuilder.ru()
            }
        }
        project.evaluationDependsOn(":")
        val arspellTask = project.tasks.getByName("arspell") as ArspellTask

        try {
            arspellTask.check()
            Assert.fail()
        } catch (e: GradleException) {
            // green
        }
    }

    @Test
    fun configurePluginWithIncorrectMdFileAndIgnoringAllIncorrectWords() {
        val mdFile = tmpFolder.newFile()
        mdFile.writeText("Hello, worllld!\n")
        val projectFolder = tmpFolder.newFolder()
        val project = ProjectBuilder.builder()
            .withProjectDir(projectFolder)
            .build()
        project.pluginManager.apply("com.nikialeksey.arspell")
        val extension = project.extensions.getByType(ArspellExtension::class.java)
        extension.md(mdFile) { builder ->
            builder.dictionary { dictBuilder ->
                dictBuilder.en()
            }
            builder.ignoreWords(listOf("worllld"))
        }
        project.evaluationDependsOn(":")
        val arspellTask = project.tasks.getByName("arspell") as ArspellTask

        try {
            arspellTask.check()
            // green
        } catch (e: GradleException) {
            Assert.fail(e.message)
        }
    }

    @Test
    fun configurePluginWithIncorrectXmlFileAndIgnoringAllIncorrectKeys() {
        val xmlFile = tmpFolder.newFile()
        xmlFile.writeText("""
            <resources>
                <string name="hello_world">Hello, world!</string>
                <string name="hello_world_bad">Hello, worllld!</string>
            </resources>
        """.trimIndent())
        val projectFolder = tmpFolder.newFolder()
        val project = ProjectBuilder.builder()
            .withProjectDir(projectFolder)
            .build()
        project.pluginManager.apply("com.nikialeksey.arspell")
        val extension = project.extensions.getByType(ArspellExtension::class.java)
        extension.android(xmlFile) { builder ->
            builder.dictionary { dictBuilder ->
                dictBuilder.en()
            }
            builder.ignoreKeys(listOf("hello_world_bad"))
        }
        project.evaluationDependsOn(":")
        val arspellTask = project.tasks.getByName("arspell") as ArspellTask

        try {
            arspellTask.check()
            // green
        } catch (e: GradleException) {
            Assert.fail(e.message)
        }
    }
}