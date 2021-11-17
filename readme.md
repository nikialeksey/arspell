# Arspell

[![Build Status](https://github.com/nikialeksey/arspell/actions/workflows/ci.yml/badge.svg)](https://github.com/nikialeksey/arspell/actions/)
[![codecov](https://codecov.io/gh/nikialeksey/arspell/branch/master/graph/badge.svg)](https://codecov.io/gh/nikialeksey/arspell)

[![Lib version](https://img.shields.io/maven-central/v/com.nikialeksey/arspell.svg?label=lib)](https://maven-badges.herokuapp.com/maven-central/com.nikialeksey/arspell)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://github.com/nikialeksey/arspell/blob/master/LICENSE)

Resources spell testing library and gradle plugin.

## How to

For example, you have two files:

`readme.md`:
```text
Hello world!
Привет, миииир!
```
`strings.xml`:
```xml
<resources>
  <string name="hello_world">Hello, world!</string>  
  <string name="hello_world_bad">Hello, worllld!</string>
</resources>
```
To check it, you should define plugin and configure it:
```groovy
plugins {
    id("com.nikialeksey.arspell").version("<latest>")
}

arspell {
    md(file("./readme.md")) {
        dictionary {
            en()
            ru()
        }
        ignoreWords(["миииир"])
    }
    android(file("./strings.xml")) {
        dictionary {
            en()
        }
        ignoreKeys(["hello_world_bad"])
    }
}
```

Then run it as gradle task:
```shell
./gradlew arspell
```

## @todo #10:30m Write docs about language tool using

## Publish
```bash
gradlew build publish closeAndReleaseRepository
```

## Changelog

- `3.1.1` - upgrade dependencies

- `3.1.0` - migrate to maven central