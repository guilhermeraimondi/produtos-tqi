package com.tqi.pagseguroprodutos.util

import com.tqi.pagseguroprodutos.application.request.CreateProductRequest
import java.math.BigDecimal

class ProductRequestCreator {

    fun createProductToBeSaved(): CreateProductRequest {
        return CreateProductRequest(
                name = "Notebook Dell G5",
                category = "Notebook",
                price =  BigDecimal(5000),
                active = true
        )
    }

    fun createProductToBeUpdated(): CreateProductRequest {
        return CreateProductRequest(
                name = "Notebook Dell G7",
                category = "Notebook",
                price =  BigDecimal(7000),
                active = true
        )
    }
}