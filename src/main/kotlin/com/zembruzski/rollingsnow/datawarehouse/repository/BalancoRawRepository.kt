package com.zembruzski.rollingsnow.datawarehouse.repository

import com.zembruzski.rollingsnow.datawarehouse.domain.RawBalanco
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface BalancoRawRepository : ElasticsearchRepository<RawBalanco, String> {

    fun findByNomeEmpresa(nomeEmpresa: String): MutableIterable<RawBalanco>

    fun findByCodigoCvm(codigoCvm: String): MutableIterable<RawBalanco>

}