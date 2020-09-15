package com.tqi.pagseguroprodutos.service

import com.querydsl.core.types.Predicate
import com.tqi.pagseguroprodutos.application.mapper.ProductMapper
import com.tqi.pagseguroprodutos.domain.ProductData
import com.tqi.pagseguroprodutos.repository.ProductRepository
import com.tqi.pagseguroprodutos.service.exception.NotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductService(
        private val productRepository: ProductRepository,
        private val productMapper: ProductMapper
) {
    fun create(productData: ProductData) {
        val product = productMapper.convertToEntity(productData)
        productRepository.save(product)
    }

    fun find(id: UUID): ProductData {
        val product = productRepository.findById(id).orElseThrow { NotFoundException() }
        return productMapper.convertToData(product)
    }

    fun findAll(predicate: Predicate): List<ProductData> {
        val products = productRepository.findAll(predicate)
        return productMapper.convertToDataList(products.toList())
    }
}