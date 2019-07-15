package com.zembruzski.rollingsnow.datawarehouse.service.helpers

import com.zembruzski.rollingsnow.datawarehouse.domain.Conta
import com.zembruzski.rollingsnow.datawarehouse.domain.PlanoContas
import com.zembruzski.rollingsnow.datawarehouse.domain.RawBalanco
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class IndicadorExtractor @Autowired constructor(
        private val indicadorMapper: IndicadorMapper
) {

    fun extraiIndicatdor(allBalances: MutableIterable<RawBalanco>, indicador: String): List<Conta> {
        return allBalances
                .map { balance ->
                    val patrimonioLiquido = indicadorMapper.extractIndicador(balance.planoContas, indicador)

                    patrimonioLiquido?.map { pl ->
                        Conta(pl.trimester, pl.year, pl.value, balance.id?.toInt())
                    }
                }
                .filterNotNull()
                .flatten()
                .groupBy { x -> Pair(x.year, x.trimester) }
                .map { x ->
                    x.value.reduce { a, b ->
                        if (a.idDocumento!! > b.idDocumento!!) a else b
                    }
                }
                .sortedWith(compareBy({it.year}, {it.trimester}))
    }

}
