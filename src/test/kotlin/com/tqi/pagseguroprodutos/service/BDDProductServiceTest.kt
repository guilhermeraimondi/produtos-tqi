package com.tqi.pagseguroprodutos.service

import com.tqi.pagseguroprodutos.application.mapper.ProductMapper
import com.tqi.pagseguroprodutos.repository.ProductRepository
import com.tqi.pagseguroprodutos.util.ProductCreator
import com.tqi.pagseguroprodutos.util.ProductDataCreator
import io.kotlintest.shouldBe
import io.kotlintest.specs.BehaviorSpec
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows
import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpClientErrorException
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class OrderServiceTest : BehaviorSpec() {

    private val productId = UUID.fromString("e34ba2a3-c55f-484f-969d-6ee73f2d242d")

    @MockK
    lateinit var productRepository: ProductRepository

    @MockK
    lateinit var productMapper: ProductMapper

    @InjectMockKs
    lateinit var productService: ProductService

    init {

        MockKAnnotations.init(this)

        Given("productId") {

            When("a productId was found") {
                val expectedProduct = ProductCreator.createProductToBeSaved(productId)
                every { productRepository.findById(productId) } returns Optional.of(expectedProduct)
                every { productMapper.convertToData(expectedProduct) } returns ProductDataCreator.create(productId)

                then("a product must have been found") {

                    val actualProduct = productService.find(productId)

                    actualProduct.id shouldBe productId

                }
            }

            When("a productId was not found") {

                every { productRepository.findById(productId) } returns Optional.empty()

                then("return a error 404 NOT_FOUND") {

                    val exception = assertThrows<HttpClientErrorException> { productService.find(productId) }
                    exception.statusCode shouldBe HttpStatus.NOT_FOUND
                }
            }
        }
    }
}