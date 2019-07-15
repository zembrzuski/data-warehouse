package com.zembruzski.rollingsnow.datawarehouse.service

import com.zembruzski.rollingsnow.datawarehouse.domain.BalancoConsolidado
import com.zembruzski.rollingsnow.datawarehouse.domain.RawBalanco
import com.zembruzski.rollingsnow.datawarehouse.repository.BalancoRawRepository
import com.zembruzski.rollingsnow.datawarehouse.service.helpers.BalancoExtractor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ConsolidadorBalancoService @Autowired constructor(
        private val balancoRawRepository: BalancoRawRepository,
        private val balancoExtractor: BalancoExtractor
) {

    fun consolidaBalanco(codigoCvm: String) {
        val allBalancesFromCompany: MutableIterable<RawBalanco> = balancoRawRepository.findByCodigoCvm(codigoCvm)

        allBalancesFromCompany.map { b ->
            b.copy(planoContas = null)
        }

        val patrimonioLiquido = balancoExtractor.extractBalanco(allBalancesFromCompany, "PATRIMONIO_LIQUIDO")

        BalancoConsolidado(
                nomeEmpresa = allBalancesFromCompany.first().nomeEmpresa,
                codigoCvm =  allBalancesFromCompany.first().codigoCvm,
                patrimonioLiquido = patrimonioLiquido,
                lucroLiquido = null
        )
    }

}