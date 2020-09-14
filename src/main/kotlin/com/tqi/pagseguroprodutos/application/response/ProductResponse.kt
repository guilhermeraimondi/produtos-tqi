package com.tqi.pagseguroprodutos.application.response

import java.math.BigDecimal
import java.util.*

data class ProductResponse(
        val id: UUID,
        val name: String,
        val category: String,
        val price: BigDecimal,
        val active: Boolean
)