package pers.easymall

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EasymallServerApplication

fun main(args: Array<String>) {
    runApplication<EasymallServerApplication>(*args)
}
