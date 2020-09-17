package com.tqi.pagseguroprodutos.repository

import java.math.BigDecimal
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Product(
        @Id
        val id: UUID = UUID.randomUUID(),
        @Column(unique=true, nullable = false)
        val name: String? = null,
        @Column(nullable = false)
        val category: String? = null,
        @Column(nullable = false)
        val price: BigDecimal? = null,
        @Column(nullable = false)
        val active: Boolean? = null
)