package com.zembruzski.rollingsnow.datawarehouse.service

import com.zembruzski.rollingsnow.datawarehouse.domain.Conta
import com.zembruzski.rollingsnow.datawarehouse.domain.PlanoContas
import com.zembruzski.rollingsnow.datawarehouse.domain.RawBalanco
import com.zembruzski.rollingsnow.datawarehouse.repository.BalancoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ImportaBalanco @Autowired constructor(
        private val balancoRepository: BalancoRepository,
        private val indicadorExtractor: IndicadorExtractor
) {


    private fun extraiIndicatdor(allBalances: MutableIterable<RawBalanco>, indicador: String): List<Conta> {
        return allBalances
                .map { balance ->
                    val patrimonioLiquido = indicadorExtractor.extractIndicador(
                            balance.planoContas, indicador)

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

    fun importaBalanco(inp: String): String {
        val allBalances = balancoRepository.findAll()

        val patrimonioLiquidoTimeline = extraiIndicatdor(allBalances, "PATRIMONIO_LIQUIDO")
        val lucroLiquidoTimeline = extraiIndicatdor(allBalances, "LUCRO_LIQUIDO")

        println("gremio")
        return inp + inp
    }

}
