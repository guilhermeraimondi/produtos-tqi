package com.tqi.pagseguroprodutos.domain

import java.math.BigDecimal
import java.util.*

data class ProductData(
        var id: UUID? = null,
        val name: String,
        val category: String,
        val price: BigDecimal,
        val active: Boolean
)