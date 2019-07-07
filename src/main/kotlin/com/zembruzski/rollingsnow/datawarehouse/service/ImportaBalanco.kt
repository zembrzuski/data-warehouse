package com.zembruzski.rollingsnow.datawarehouse.service

import com.zembruzski.rollingsnow.datawarehouse.repository.BalancoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ImportaBalanco @Autowired constructor(
        private val balancoRepository: BalancoRepository
) {

    fun importaBalanco(inp: String): String {
        val findAll = balancoRepository.findAll()
        println(inp)
        return inp + inp
    }

}
