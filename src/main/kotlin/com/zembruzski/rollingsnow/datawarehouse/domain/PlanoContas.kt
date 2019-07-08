package com.zembruzski.rollingsnow.datawarehouse.domain

data class PlanoContas (

        val patrimonioLiquido: List<Conta>? = null,
        val lucroLiquido: List<Conta>? = null

)