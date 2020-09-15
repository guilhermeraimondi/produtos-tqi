package com.tqi.pagseguroprodutos.application.mapper

import com.tqi.pagseguroprodutos.application.request.CreateProductRequest
import com.tqi.pagseguroprodutos.application.response.ProductResponse
import com.tqi.pagseguroprodutos.domain.ProductData
import com.tqi.pagseguroprodutos.repository.Product

interface ProductMapper {

    fun convertToData(productRequest: CreateProductRequest): ProductData
    fun convertToData(product: Product): ProductData
    fun convertToDataList(products: List<Product>): List<ProductData>
    fun convertToEntity(productData: ProductData): Product
    fun convertToResponse(productData: ProductData): ProductResponse
    fun convertToResponseList(productDataList: List<ProductData>): List<ProductResponse>
}