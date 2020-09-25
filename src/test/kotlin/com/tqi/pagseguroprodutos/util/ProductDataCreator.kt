package com.tqi.pagseguroprodutos.util

import com.tqi.pagseguroprodutos.domain.ProductData
import java.math.BigDecimal
import java.util.*

object ProductDataCreator {
    fun create(uuid: UUID): ProductData {
        return ProductData(
                id = uuid,
                name = "Notebook Dell G5",
                category = "Notebook",
                price =  BigDecimal(5000),
                active = true
        )
    }

    fun create(): ProductData {
        return ProductData(
                name = "Notebook Dell G5",
                category = "Notebook",
                price =  BigDecimal(5000),
                active = true
        )
    }

    fun createProductToBeUpdated(): ProductData {
        return ProductData(
                id = UUID.randomUUID(),
                name = "Notebook Dell G7",
                category = "Notebook",
                price =  BigDecimal(7000),
                active = true
        )
    }
}