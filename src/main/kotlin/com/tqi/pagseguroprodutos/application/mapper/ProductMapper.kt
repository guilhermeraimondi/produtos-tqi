package com.tqi.pagseguroprodutos.application.mapper

import com.tqi.pagseguroprodutos.application.request.CreateProductRequest
import com.tqi.pagseguroprodutos.domain.ProductData
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface ProductMapper {
    fun convertToData(productRequest: CreateProductRequest): ProductData
}