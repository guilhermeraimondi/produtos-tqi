package com.tqi.pagseguroprodutos.application.controller

import com.tqi.pagseguroprodutos.application.mapper.ProductMapper
import com.tqi.pagseguroprodutos.service.ProductService
import com.tqi.pagseguroprodutos.util.ProductCreator
import com.tqi.pagseguroprodutos.util.ProductDataCreator
import com.tqi.pagseguroprodutos.util.ProductRequestCreator
import com.tqi.pagseguroprodutos.util.ProductResponseCreator
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@ExtendWith(SpringExtension::class)
class ProductControllerTest {

    @InjectMocks
    private lateinit var productController: ProductController

    @Mock
    private lateinit var productService: ProductService

    @Mock
    private lateinit var productMapper: ProductMapper

    private val createProductToBeUpdated = ProductRequestCreator().createProductToBeUpdated()
    private val productData = ProductDataCreator().createProductToBeUpdated()
    private val productResponse = ProductResponseCreator().createProductResponse()
    private val productRequestCreator = ProductRequestCreator().createProductToBeUpdated()

    @BeforeEach
    fun setUp() {
        Mockito.`when`(productMapper.convertToData(productRequestCreator)).thenReturn(productData)
        Mockito.`when`(productService.update(productData)).thenReturn(productData)
        Mockito.`when`(productMapper.convertToResponse(productData)).thenReturn(productResponse)
        Mockito.doNothing().`when`(productService).delete(UUID.randomUUID())
    }

    @Test
    fun updateProductSuccess() {
        val id = UUID.randomUUID()
        val response = productController.update(id, createProductToBeUpdated)
        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(response.body).isEqualTo(productResponse)
    }

    @Test
    fun deleteProductSuccess() {
        val id = UUID.randomUUID()
        val response = productController.delete(id)
        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.NO_CONTENT)
    }
}