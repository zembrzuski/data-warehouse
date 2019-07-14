package com.zembruzski.rollingsnow.datawarehouse.service

import com.zembruzski.rollingsnow.datawarehouse.domain.Conta
import com.zembruzski.rollingsnow.datawarehouse.domain.PlanoContas
import org.springframework.stereotype.Component

@Component
class IndicadorExtractor() {

    fun extractIndicador(planoContas: PlanoContas?, indicador: String): List<Conta>? {
        val mapper = mapOf(
                "PATRIMONIO_LIQUIDO" to planoContas?.patrimonioLiquido,
                "LUCRO_LIQUIDO" to planoContas?.lucroLiquido
        )

        return mapper[indicador]
    }

}
