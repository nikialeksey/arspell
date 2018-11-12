package com.nikialeksey.arspell

interface Error {
    fun key(): String
    fun word(): String
    fun suggests(): List<String>
}