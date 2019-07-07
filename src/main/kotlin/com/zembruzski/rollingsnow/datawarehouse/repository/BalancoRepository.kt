package com.zembruzski.rollingsnow.datawarehouse.repository

import com.zembruzski.rollingsnow.datawarehouse.domain.RawBalanco
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface BalancoRepository : ElasticsearchRepository<RawBalanco, String>