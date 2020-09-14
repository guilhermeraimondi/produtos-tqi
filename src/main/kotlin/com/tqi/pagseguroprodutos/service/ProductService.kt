package com.tqi.pagseguroprodutos.service

import com.tqi.pagseguroprodutos.domain.ProductData
import com.tqi.pagseguroprodutos.repository.Product
import com.tqi.pagseguroprodutos.repository.ProductRepository

class ProductService(
        private val productRepository: ProductRepository
) {
    fun createProduct(productData: ProductData) {
        val product = Product(
                name = productData.name,
                category = productData.category,
                price = productData.price,
                active = productData.active
        )

        productRepository.save(product)
    }

}