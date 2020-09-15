package com.tqi.pagseguroprodutos.application.mapper.impl

import com.tqi.pagseguroprodutos.application.mapper.ProductMapper
import com.tqi.pagseguroprodutos.application.request.CreateProductRequest
import com.tqi.pagseguroprodutos.application.response.ProductResponse
import com.tqi.pagseguroprodutos.domain.ProductData
import com.tqi.pagseguroprodutos.repository.Product
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

    override fun convertToData(product: Product): ProductData {
        return ProductData(
                id = product.id,
                name = product.name!!,
                category = product.category!!,
                price = product.price!!,
                active = product.active!!
        )
    }

    override fun convertToDataList(products: List<Product>): List<ProductData> {
        return products.map { convertToData(it) }
    }

    override fun convertToEntity(productData: ProductData): Product {
        return Product(
                name = productData.name,
                category = productData.category,
                price = productData.price,
                active = productData.active
        )
    }

    override fun convertToResponse(productData: ProductData): ProductResponse {
        return ProductResponse(
                id = productData.id!!,
                name = productData.name,
                category = productData.category,
                price = productData.price,
                active = productData.active
        )
    }

    override fun convertToResponseList(productDataList: List<ProductData>): List<ProductResponse> {
        return productDataList.map { convertToResponse(it) }
    }
}