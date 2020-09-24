package com.tqi.pagseguroprodutos.application.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.tqi.pagseguroprodutos.application.mapper.ProductMapper
import com.tqi.pagseguroprodutos.service.ProductService
import com.tqi.pagseguroprodutos.util.ProductDataCreator
import com.tqi.pagseguroprodutos.util.ProductRequestCreator
import com.tqi.pagseguroprodutos.util.ProductResponseCreator
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

@ExtendWith(SpringExtension::class)
@WebMvcTest(ProductController::class)
class ProductControllerTest {

    @Autowired
    lateinit private var mockMvc: MockMvc

    @MockBean
    private lateinit var productService: ProductService

    @MockBean
    private lateinit var productMapper: ProductMapper

    private val createProductToBeUpdated = ProductRequestCreator().createProductToBeUpdated()
    private val productData = ProductDataCreator().createProductToBeUpdated()
    private val productResponse = ProductResponseCreator().createProductResponse()
    private val productRequestCreator = ProductRequestCreator().createProductToBeUpdated()
    private val id = UUID.randomUUID()
    private val mapper = ObjectMapper()

    @BeforeEach
    fun setUp() {
        Mockito.`when`(productMapper.convertToData(productRequestCreator)).thenReturn(productData)
        Mockito.`when`(productService.update(productData)).thenReturn(productData)
        Mockito.`when`(productMapper.convertToResponse(productData)).thenReturn(productResponse)
        Mockito.doNothing().`when`(productService).delete(UUID.randomUUID())
    }

    @Test
    fun findAllProductSuccess() {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk)
    }

    @Test
    fun updateProductSuccess() {
        val response = mockMvc.perform(put("/products/{id}", id)
                .content(mapper.writeValueAsString(createProductToBeUpdated))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andExpect { response -> response.response.contentAsString.equals(mapper.writeValueAsString(productResponse)) }
                .andReturn()

        Assertions.assertThat(response.response.contentAsString).isEqualTo(mapper.writeValueAsString(productResponse))
    }

    @Test
    fun deleteProductSuccess() {
        mockMvc.perform(delete("/products/{id}", id))
                .andExpect(status().isNoContent)
    }
}