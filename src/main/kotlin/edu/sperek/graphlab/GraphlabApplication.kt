package edu.sperek.graphlab

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GraphlabApplication

fun main(args: Array<String>) {
    runApplication<GraphlabApplication>(*args)
}
