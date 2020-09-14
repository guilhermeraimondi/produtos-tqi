package com.tqi.pagseguroprodutos.domain

import java.math.BigDecimal

data class ProductData(
        val name: String,
        val category: String,
        val price: BigDecimal,
        val active: Boolean
)