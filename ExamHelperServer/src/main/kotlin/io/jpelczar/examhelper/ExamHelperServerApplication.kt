package io.jpelczar.examhelper

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ExamHelperServerApplication

fun main(args: Array<String>) {
    SpringApplication.run(ExamHelperServerApplication::class.java, *args)
}
