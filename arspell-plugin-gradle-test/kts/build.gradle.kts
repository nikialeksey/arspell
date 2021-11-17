plugins {
    id("com.nikialeksey.arspell")
}

arspell {
    md(file("./readme.md")) {
        dictionary {
            en()
            group {
                ru()
                en()
            }
        }
    }
}