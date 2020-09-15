package com.tqi.pagseguroprodutos.application.controller

import com.querydsl.core.types.Predicate
import com.tqi.pagseguroprodutos.application.mapper.ProductMapper
import com.tqi.pagseguroprodutos.application.request.CreateProductRequest
import com.tqi.pagseguroprodutos.application.response.ProductResponse
import com.tqi.pagseguroprodutos.repository.Product
import com.tqi.pagseguroprodutos.service.ProductService
import org.springframework.data.querydsl.binding.QuerydslPredicate
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/products", produces = [APPLICATION_JSON_VALUE])
class ProductController(
        private val productService: ProductService,
        private val productMapper: ProductMapper
) {
    @ResponseStatus(CREATED)
    @PostMapping
    fun create(@RequestBody productRequest: CreateProductRequest) {
        productService.create(productMapper.convertToData(productRequest))
    }

    @ResponseStatus(OK)
    @GetMapping("/{id}")
    fun find(@PathVariable id: UUID): ProductResponse {
        val product = productService.find(id)
        return productMapper.convertToResponse(product)
    }

    @ResponseStatus(OK)
    @GetMapping
    fun findAll(@QuerydslPredicate(root = Product::class) predicate: Predicate): List<ProductResponse> {
        val products = productService.findAll(predicate)
        return productMapper.convertToResponseList(products)
    }
}