package com.zembruzski.rollingsnow.datawarehouse.domain

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document

@Document(indexName = "teste", type = "teste")
data class RawBalanco(

        @Id
        val id: String,

        @JsonProperty("nome_empresa")
        val nomeEmpresa: String,

        @JsonProperty("codigo_cvm")
        val codigoCvm: String,

        @JsonProperty("numero_documento_original")
        val numeroDocumentoOriginal: Int,

        @JsonProperty("data_entrega_documento")
        val dataEntregaDocumento: String,

        @JsonProperty("id_documento")
        val idDocumento: Int,

        @JsonProperty("tipo_documento")
        val tipoDocumento: String,

        @JsonProperty("plano_contas")
        val planoContas: PlanoContas
)