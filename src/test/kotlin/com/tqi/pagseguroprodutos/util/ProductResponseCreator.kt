package com.tqi.pagseguroprodutos.util

import com.tqi.pagseguroprodutos.application.response.ProductResponse
import java.math.BigDecimal
import java.util.*

class ProductResponseCreator {

    fun createProductResponse(): ProductResponse {
        return ProductResponse(
                id = UUID.randomUUID(),
                name = "Notebook Dell G7",
                category = "Notebook",
                price =  BigDecimal(7000),
                active = true
        )
    }
}