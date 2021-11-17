package com.nikialeksey.arspell

import com.nikialeksey.arspell.words.SimpleWord
import org.gradle.api.GradleException
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Assert
import org.junit.Test

class ArspellTaskTest {

    @Test
    fun checkWithoutErrors() {
        val project = ProjectBuilder.builder().build()
        project.tasks.create("arspell", ArspellTask::class.java) { task ->
            task.arspell = Arspell.Fake(emptyList())
        }
        val task = project.tasks.getByName("arspell") as ArspellTask

        try {
            task.check()
            // green
        } catch (e: GradleException) {
            Assert.fail(e.message)
        }
    }

    @Test
    fun checkWithErrors() {
        val project = ProjectBuilder.builder().build()
        project.tasks.create("arspell", ArspellTask::class.java) { task ->
            task.arspell = Arspell.Fake(
                listOf(
                    SimpleError(
                        SimpleWord("key", "word")
                    )
                )
            )
        }
        val task = project.tasks.getByName("arspell") as ArspellTask

        try {
            task.check()
            Assert.fail()
        } catch (e: GradleException) {
            // green
        }
    }
}