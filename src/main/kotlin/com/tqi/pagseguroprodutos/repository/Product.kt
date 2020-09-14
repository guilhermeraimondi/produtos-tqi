package com.tqi.pagseguroprodutos.repository

import java.math.BigDecimal
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Product (
        @Id
        val id: UUID = UUID.randomUUID(),
        val name: String,
        val category: String,
        val price: BigDecimal,
        val active: Boolean
)