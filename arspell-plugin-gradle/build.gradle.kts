plugins {
    kotlin("jvm")
    id("java-gradle-plugin")
    id("maven-publish")
    id("com.gradle.plugin-publish").version("0.18.0")
}

apply {
    from("../gradle/jacoco.gradle")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

val projectVersion: String by extra("libraryVersion")
group = "com.nikialeksey"
version = projectVersion

pluginBundle {
    website = "https://github.com/nikialeksey/arspell"
    vcsUrl = "https://github.com/nikialeksey/arspell.git"
    description = "Arspell - resources spell checking plugin"
    tags = listOf("spell-checking", "hunspell", "language-tool")

    mavenCoordinates {
        groupId = project.group.toString()
        artifactId = "arspell-plugin-gradle"
        version = project.version.toString()
    }
}

gradlePlugin {
    plugins {
        create("arspellPlugin") {
            id = "com.nikialeksey.arspell"
            displayName = "arspell"
            implementationClass = "com.nikialeksey.arspell.ArspellPlugin"
        }
    }
}

dependencies {
    implementation(project(":arspell"))
    implementation(project(":arspell-hunspell"))
    implementation(project(":arspell-md"))
    implementation(project(":arspell-android"))
    implementation("org.apache.commons:commons-io:1.3.2")
    testImplementation("junit:junit:4.13.2")
}
