package com.zembruzski.rollingsnow.datawarehouse.service

import com.zembruzski.rollingsnow.datawarehouse.domain.RawBalanco
import com.zembruzski.rollingsnow.datawarehouse.repository.BalancoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class ImportaBalanco @Autowired constructor(
        private val balancoRepository: BalancoRepository
) {

    fun importaBalanco(inp: String): String {
        // balancoRepository.save(RawBalanco(nome_empresa = "asjdkfla"))
        val findAll = balancoRepository.findAll()
        println(inp)
        return inp + inp
    }

}
