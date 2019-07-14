package com.zembruzski.rollingsnow.datawarehouse.service

import com.zembruzski.rollingsnow.datawarehouse.domain.Conta
import com.zembruzski.rollingsnow.datawarehouse.domain.PlanoContas
import com.zembruzski.rollingsnow.datawarehouse.domain.RawBalanco
import org.springframework.stereotype.Component

@Component
class IndicadorExtractor() {

    private fun extractIndicador(planoContas: PlanoContas?, indicador: String): List<Conta>? {
        val mapper = mapOf(
                "PATRIMONIO_LIQUIDO" to planoContas?.patrimonioLiquido,
                "LUCRO_LIQUIDO" to planoContas?.lucroLiquido
        )

        return mapper[indicador]
    }

    fun extraiIndicatdor(allBalances: MutableIterable<RawBalanco>, indicador: String): List<Conta> {
        return allBalances
                .map { balance ->
                    val patrimonioLiquido = extractIndicador(balance.planoContas, indicador)

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
