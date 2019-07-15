package com.zembruzski.rollingsnow.datawarehouse.service.helpers

import com.zembruzski.rollingsnow.datawarehouse.domain.Conta
import com.zembruzski.rollingsnow.datawarehouse.domain.PlanoContas
import com.zembruzski.rollingsnow.datawarehouse.domain.RawBalanco
import org.springframework.stereotype.Component

@Component
class IndicadorMapper {

    fun extractIndicador(planoContas: PlanoContas?, indicador: String): List<Conta>? {
        val mapper = mapOf(
                "PATRIMONIO_LIQUIDO" to planoContas?.patrimonioLiquido,
                "LUCRO_LIQUIDO" to planoContas?.lucroLiquido
        )

        return mapper[indicador]
    }

    fun extractIndicador(allBalancesFromCompany: MutableIterable<RawBalanco>, indicador: String): List<Conta>? {
        // TODO refatorar isso
        return allBalancesFromCompany
                .map { balance ->

                    extractIndicador(balance.planoContas, indicador)

                    val patrimonioLiquido = balance.planoContas?.patrimonioLiquido?.map { pl ->
                        Conta(pl.trimester, pl.year, pl.value, balance.numeroDocumentoOriginal)
                    }

                    val lucroLiquido = balance.planoContas?.patrimonioLiquido?.map { pl ->
                        Conta(pl.trimester, pl.year, pl.value, balance.numeroDocumentoOriginal)
                    }

                    PlanoContas(patrimonioLiquido, lucroLiquido)
                }
                .map { planoContas -> extractIndicador(planoContas, indicador) }
                .filterNotNull()
                .flatten()
    }

}