# Arspell

[![Build Status][build-status-badge]][build-status-link]
[![codecov][codecov-badge]][codecov-link]

[![Lib version][lib-version-badge]][lib-version-link]
[![Gradle plugin version][gradle-plugin-badge]][gradle-plugin-link]
[![License: MIT][license-badge]][license-link]

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

[build-status-badge]: https://github.com/nikialeksey/arspell/actions/workflows/ci.yml/badge.svg
[build-status-link]: https://github.com/nikialeksey/arspell/actions/
[codecov-badge]: https://codecov.io/gh/nikialeksey/arspell/branch/master/graph/badge.svg
[codecov-link]: https://codecov.io/gh/nikialeksey/arspell
[lib-version-badge]: https://img.shields.io/maven-central/v/com.nikialeksey/arspell.svg?label=lib
[lib-version-link]: https://maven-badges.herokuapp.com/maven-central/com.nikialeksey/arspell
[gradle-plugin-badge]: https://img.shields.io/maven-metadata/v/https/plugins.gradle.org/m2/com/nikialeksey/arspell-plugin-gradle/maven-metadata.xml.svg?label=plugin
[gradle-plugin-link]: https://plugins.gradle.org/plugin/dev.youshallnotpass
[license-badge]: https://img.shields.io/badge/License-MIT-yellow.svg
[license-link]: https://github.com/nikialeksey/arspell/blob/master/LICENSE
