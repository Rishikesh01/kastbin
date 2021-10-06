package com.kastbin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KastbinApplication

fun main(args: Array<String>) {
    runApplication<KastbinApplication>(*args)
}


