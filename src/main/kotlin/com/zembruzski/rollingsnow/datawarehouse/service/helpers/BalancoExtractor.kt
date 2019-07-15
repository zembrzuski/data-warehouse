package com.zembruzski.rollingsnow.datawarehouse.service.helpers

import com.zembruzski.rollingsnow.datawarehouse.domain.Conta
import com.zembruzski.rollingsnow.datawarehouse.domain.RawBalanco
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class BalancoExtractor @Autowired constructor(
        private val indicadorMapper: IndicadorMapper
){

    fun extractBalanco(allBalancesFromCompany: MutableIterable<RawBalanco>, indicador: String): List<Conta>? {
        val naosei = indicadorMapper.extractIndicador(allBalancesFromCompany, indicador)

        println("xoxo")

        return null
    }

}