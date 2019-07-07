package com.zembruzski.rollingsnow.datawarehouse

import com.zembruzski.rollingsnow.datawarehouse.service.ImportaBalanco
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories


@SpringBootApplication
@EnableElasticsearchRepositories("com.zembruzski.rollingsnow.datawarehouse.repository")
class DatawareHouseApplication @Autowired constructor(
        private val importaBalanco: ImportaBalanco
) {

    @Bean
    fun init() = CommandLineRunner {
        importaBalanco.importaBalanco("009342")
    }

}

fun main(args: Array<String>) {
    runApplication<DatawareHouseApplication>(*args)
}

