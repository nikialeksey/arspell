package com.nikialeksey.arspell

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File
import javax.inject.Inject

class ArspellPlugin(
    private val extensionName: String,
    private val taskName: String
) : Plugin<Project> {

    @Inject
    constructor() : this("arspell", "arspell")

    override fun apply(target: Project) {
        target.extensions.create(
            extensionName,
            ArspellExtension::class.java,
            File(target.rootProject.buildDir, extensionName)
        )
        target.afterEvaluate {
            target.tasks.register(taskName, ArspellTask::class.java) { task ->
                val extension = target.extensions.getByType(ArspellExtension::class.java)
                val arspell = extension.arspell()
                task.arspell = arspell
            }
        }
    }

}