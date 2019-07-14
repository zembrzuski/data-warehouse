package com.zembruzski.rollingsnow.datawarehouse

import com.zembruzski.rollingsnow.datawarehouse.service.ImportaBalancoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories


@SpringBootApplication
@EnableElasticsearchRepositories("com.zembruzski.rollingsnow.datawarehouse.repository")
class DatawareHouseApplication @Autowired constructor(
        private val importaBalancoService: ImportaBalancoService
) {

    @Bean
    fun init() = CommandLineRunner {
        val id = importaBalancoService.importaBalanco("009342")
        println("generated " + id)
    }

}

fun main(args: Array<String>) {
    runApplication<DatawareHouseApplication>(*args)
}

