package com.zembruzski.rollingsnow.datawarehouse.domain

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(indexName = "zembrzuski", type = "consolidates_balance")
data class BalancoConsolidado (

        @Id
        var id: String? = null,
        var nomeEmpresa: String? = null,
        var codigoCvm: String? = null,
        var patrimonioLiquido: List<Conta>? = null,
        var lucroLiquido: List<Conta>? = null

)