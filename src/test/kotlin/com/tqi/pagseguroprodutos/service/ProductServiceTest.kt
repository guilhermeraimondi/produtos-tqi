package com.tqi.pagseguroprodutos.service

import com.tqi.pagseguroprodutos.application.mapper.ProductMapper
import com.tqi.pagseguroprodutos.repository.ProductRepository
import com.tqi.pagseguroprodutos.service.exception.NotFoundException
import com.tqi.pagseguroprodutos.util.ProductCreator
import com.tqi.pagseguroprodutos.util.ProductDataCreator
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@ExtendWith(SpringExtension::class)
class ProductServiceTest {

    @InjectMocks
    private lateinit var productService: ProductService

    @Mock
    private lateinit var productRepository: ProductRepository

    @Mock
    private lateinit var productMapper: ProductMapper

    private val productDataToBeUpdated = ProductDataCreator().createProductToBeUpdated()

    private val productToBeUpdated = ProductCreator().createProductToBeUpdated()

    @BeforeEach
    fun setUp() {
        Mockito.`when`(productMapper.convertToEntity(productDataToBeUpdated)).thenReturn(productToBeUpdated)
        Mockito.`when`(productRepository.save(productToBeUpdated)).thenReturn(productToBeUpdated)
        Mockito.`when`(productMapper.convertToData(productToBeUpdated)).thenReturn(productDataToBeUpdated)
        Mockito.`when`(productRepository.findById(productToBeUpdated.id)).thenReturn(Optional.of(productToBeUpdated))
        Mockito.doNothing().`when`(productRepository).delete(productToBeUpdated)
    }

    @Test
    fun updateProductSuccess() {
        val update = productService.update(productDataToBeUpdated)
        Assertions.assertThat(update).isEqualTo(productDataToBeUpdated);
    }

    @Test
    fun deleteProductSuccess() {
        // Todo - qual melhor forma de testar metodos void
        productService.delete(productToBeUpdated.id)
    }

    @Test
    fun deleteProductNotFound() {
        Mockito.`when`(productRepository.findById(UUID.randomUUID())).thenThrow(NotFoundException())
        val id = UUID.randomUUID()
        org.junit.jupiter.api.Assertions.assertThrows(NotFoundException::class.java) {
            productService.delete(id)
        }
    }
}