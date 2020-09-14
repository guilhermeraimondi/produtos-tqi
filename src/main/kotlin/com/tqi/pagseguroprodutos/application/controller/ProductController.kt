package com.tqi.pagseguroprodutos.application.controller

import com.tqi.pagseguroprodutos.application.mapper.ProductMapper
import com.tqi.pagseguroprodutos.application.request.CreateProductRequest
import com.tqi.pagseguroprodutos.service.ProductService
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products", produces = [APPLICATION_JSON_VALUE])
class ProductController(
        private val productService: ProductService,
        private val productMapper: ProductMapper
) {
    @ResponseStatus(CREATED)
    @PostMapping
    fun createProduct(@RequestBody productRequest: CreateProductRequest) {
        productService.createProduct(productMapper.convertToData(productRequest))
    }
}