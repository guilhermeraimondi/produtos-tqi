package com.tqi.pagseguroprodutos.repository

import java.math.BigDecimal
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Product (
        @Id
        val id: UUID = UUID.randomUUID(),
        val name: String? = null,
        val category: String? = null,
        val price: BigDecimal? = null,
        val active: Boolean? = null
)