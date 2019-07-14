package com.zembruzski.rollingsnow.datawarehouse.domain

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(indexName = "balanco_bonito", type = "balanco_bonito")
data class BalancoForExhibition (

        @Id
        var id: String? = null,

        val nomeEmpresa: String,
        val codigoCvm: String,
        val patrimonioLiquido: List<Conta>,
        val lucroLiquido: List<Conta>
)