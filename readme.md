# Arspell

[![Build Status](https://travis-ci.org/nikialeksey/arspell.svg?branch=master)](https://travis-ci.org/nikialeksey/arspell)
[![codecov](https://codecov.io/gh/nikialeksey/arspell/branch/master/graph/badge.svg)](https://codecov.io/gh/nikialeksey/arspell)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://github.com/nikialeksey/arspell/blob/master/LICENSE)
[![Download](https://api.bintray.com/packages/nikialeksey/android/arspell/images/download.svg)](https://bintray.com/nikialeksey/android/arspell/_latestVersion)

Resource spell testing library

## How to
```gradle
dependencies {
    testImplementation 'com.nikialeksey:arspell:3.0.2'
    testImplementation 'com.nikialeksey:arspell-hunspell:3.0.2'
    testImplementation 'com.nikialeksey:arspell-android:3.0.2'
}
```

```kotlin
class ResourcesTest {
    @Test
    fun enSpell() {
        val errors = DictionarySpell(
            HunspellDictionary(
                Hunspell(
                    "./src/test/assets/en_US/index.dic",
                    "./src/test/assets/en_US/index.aff"
                )
            ),
            AndroidStrings(File("./src/main/res/values-en/strings.xml"))
        ).check()
        Assert.assertTrue(ErrorMessage(errors).asString(), errors.isEmpty())
    }
}
```

## Ignoring words and keys
```kotlin
class ResourcesTest {
    @Test
    fun enSpell() {
        val errors = DictionarySpell(
            HunspellDictionary(
                Hunspell(
                    "./src/test/assets/en_US/index.dic",
                    "./src/test/assets/en_US/index.aff"
                )
            ),
            IgnoreValuesStrings(
                IgnoreKeysStrings(
                    AndroidStrings(File("./src/test/res/values/dictionary.xml")),
                    setOf("ignored")
                ),
                setOf("QWE")
            )
        ).check()
        Assert.assertTrue(ErrorMessage(errors).asString(), errors.isEmpty())
    }
}
```

## Checking every word in multiple dictionaries
```kotlin
class ResourcesTest {
    @Test
    fun ruEnSpell() {
        val errors = DictionarySpell(
            GroupSpell(
                listOf(
                    HunspellDictionary(
                        Hunspell(
                            "./src/test/assets/en_US/index.dic",
                            "./src/test/assets/en_US/index.aff"
                        )
                    ),
                    HunspellDictionary(
                        Hunspell(
                            "./src/test/assets/ru/index.dic",
                            "./src/test/assets/ru/index.aff"
                        )
                    )
                )
            ),
            AndroidStrings(File("./src/test/res/values/dictionary.xml"))
        ).check()
        Assert.assertTrue(ErrorMessage(errors).asString(), errors.isEmpty())
    }
}
```

## Hunspell dictionaries

[Dictionaries](https://github.com/wooorm/dictionaries)

[Hunspell man](https://www.systutorials.com/docs/linux/man/4-hunspell/) for adding new words in any dictionary.

## @todo #10:30m Write docs about language tool using

## Publish
```bash
gradlew install
gradlew bintrayUpload
```