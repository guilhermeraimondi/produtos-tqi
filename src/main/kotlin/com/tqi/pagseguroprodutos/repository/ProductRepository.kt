package com.tqi.pagseguroprodutos.repository

import org.springframework.data.repository.CrudRepository
import java.util.*

interface ProductRepository: CrudRepository<Product, UUID> {
}