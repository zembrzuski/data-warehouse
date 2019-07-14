package com.zembruzski.rollingsnow.datawarehouse.service

import com.zembruzski.rollingsnow.datawarehouse.domain.BalancoForExhibition
import com.zembruzski.rollingsnow.datawarehouse.repository.BalancoFineRepository
import com.zembruzski.rollingsnow.datawarehouse.repository.BalancoRawRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ImportaBalancoService @Autowired constructor(
        private val balancoRawRepository: BalancoRawRepository,
        private val indicadorExtractor: IndicadorExtractor,
        private val balancoFineRepository: BalancoFineRepository
) {

    fun importaBalanco(inp: String): String? {
        val allBalances = balancoRawRepository.findByNomeEmpresa("DIMED")

        // TODO eh preferivel lancar excecao do que deixar as bagaca empty

        val balancoForExhibition = BalancoForExhibition(
                nomeEmpresa = allBalances.first().nomeEmpresa.orEmpty(),
                codigoCvm = allBalances.first().codigoCvm.orEmpty(),
                patrimonioLiquido = indicadorExtractor.extraiIndicatdor(allBalances, "PATRIMONIO_LIQUIDO"),
                lucroLiquido = indicadorExtractor.extraiIndicatdor(allBalances, "LUCRO_LIQUIDO")
        )

        return balancoFineRepository.save(balancoForExhibition).id
    }

}
