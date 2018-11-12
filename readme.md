# Arspell

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://github.com/nikialeksey/arspell/blob/master/LICENSE)
[![API](https://img.shields.io/badge/API-16%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=16)

Android resource spell testing library

## How to
```gradle
dependencies {
    testImplementation 'com.nikialeksey:arspell:0.0.2'
}
```

```kotlin
class ResourcesTest {
    @Test
    fun enSpell() {
        val errors = HunspellCheck(
            Hunspell(
                "./src/test/assets/en_US/index.dic",
                "./src/test/assets/en_US/index.aff"
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
        val errors = HunspellCheck(
            Hunspell(
                "./src/test/assets/en_US/index.dic",
                "./src/test/assets/en_US/index.aff"
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

## Hunspell dictionaries

[Dictionaries](https://github.com/wooorm/dictionaries)

[Hunspell man](https://www.systutorials.com/docs/linux/man/4-hunspell/) for adding new words in any dictionary.

## Publish
```bash
gradlew install
gradlew bintrayUpload
```