package com.zembruzski.rollingsnow.datawarehouse.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class PlanoContas (

        @JsonProperty("patrimonio_liquido")
        val patrimonioLiquido: Conta,

        @JsonProperty("lucro_liquido")
        val lucroLiquido: Conta
)