package com.nikialeksey.arspell

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

open class ArspellTask : DefaultTask() {

    @Input
    lateinit var arspell: Arspell

    @TaskAction
    fun check() {
        val errors = arspell.check()
        if (errors.isNotEmpty()) {
            val message = ErrorMessage(errors).asString()
            logger.error(message)
            throw GradleException("Spell checks failed! Found ${errors.size} issues.")
        }
    }
}