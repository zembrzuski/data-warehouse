package com.zembruzski.rollingsnow.datawarehouse.domain

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(indexName = "zembrzuski", type = "raw_balances")
data class RawBalanco(

        @Id
        val id: String? = null,
        var nomeEmpresa: String? = null,
        val codigoCvm: String? = null,
        val numeroDocumentoOriginal: Int? = null,
        val dataEntregaDocumento: Date? = null,
        val idDocumento: Int? = null,
        val tipoDocumento: String? = null,
        val planoContas: PlanoContas? = null

)