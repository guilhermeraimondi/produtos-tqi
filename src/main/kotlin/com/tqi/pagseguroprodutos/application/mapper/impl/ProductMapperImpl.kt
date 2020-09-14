package com.tqi.pagseguroprodutos.application.mapper.impl

import com.tqi.pagseguroprodutos.application.mapper.ProductMapper
import com.tqi.pagseguroprodutos.application.request.CreateProductRequest
import com.tqi.pagseguroprodutos.domain.ProductData
import org.springframework.stereotype.Component

@Component
class ProductMapperImpl : ProductMapper {
    override fun convertToData(productRequest: CreateProductRequest): ProductData {
        return ProductData(
                name = productRequest.name,
                category = productRequest.category,
                price = productRequest.price,
                active = productRequest.active
        )
    }
}