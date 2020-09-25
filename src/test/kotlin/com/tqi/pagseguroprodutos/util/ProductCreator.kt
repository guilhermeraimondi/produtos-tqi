package com.tqi.pagseguroprodutos.util

import com.tqi.pagseguroprodutos.repository.Product
import java.math.BigDecimal
import java.util.*

object ProductCreator {
    fun createProductToBeSaved(id: UUID): Product {
        return Product(
                id = id,
                name = "Notebook Dell G5",
                category = "Notebook",
                price =  BigDecimal(5000),
                active = true
        )
    }

    fun createProductToBeSaved(): Product {
        return Product(
                name = "Notebook Dell G5",
                category = "Notebook",
                price =  BigDecimal(5000),
                active = true
        )
    }

    fun createProductToBeUpdated(): Product {
        return Product(
                id = UUID.randomUUID(),
                name = "Notebook Dell G7",
                category = "Notebook",
                price =  BigDecimal(7000),
                active = true
        )
    }
}