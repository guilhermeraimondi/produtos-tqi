package com.tqi.pagseguroprodutos.application.request

import java.math.BigDecimal

data class CreateProductRequest(
        val name: String,
        val category: String,
        val price: BigDecimal,
        val active: Boolean = true
)