package com.tqi.pagseguroprodutos.application.controller

import com.tqi.pagseguroprodutos.application.mapper.ProductMapper
import com.tqi.pagseguroprodutos.application.request.CreateProductRequest
import com.tqi.pagseguroprodutos.application.response.ExternalApiObject
import com.tqi.pagseguroprodutos.application.response.ProductResponse
import com.tqi.pagseguroprodutos.service.ProductService
import io.swagger.annotations.*
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import java.util.*

@Api(value = "Informação sobre produtos", tags = ["produto"])
@RestController
@RequestMapping("/products", produces = [APPLICATION_JSON_VALUE])
class ProductController(
        private val productService: ProductService,
        private val productMapper: ProductMapper
) {


    @GetMapping("/external-api")
    fun getItemExternalApi(): ExternalApiObject {
        return productService.getItemExternalApi()
    }

    @ResponseStatus(CREATED)
    @PostMapping
    @ApiOperation(value = "Salvar um novo produto", tags = ["produto"])
    @ApiResponses(*[
        ApiResponse(code = 201, message = "Sucesso, produto cadastrado")
    ])
    fun create(@RequestBody productRequest: CreateProductRequest) {
        productService.create(productMapper.convertToData(productRequest))
    }

    @ResponseStatus(OK)
    @GetMapping("/{id}")
    @ApiOperation(value = "Buscar produto por id", tags = ["produto"])
    @ApiResponses(*[
        ApiResponse(code = 200, message = "Sucesso, dado encontrado"),
        ApiResponse(code = 404, message = "Dado não encontrado")
    ])
    fun find(@PathVariable id: UUID): ProductResponse {
        val product = productService.find(id)
        return productMapper.convertToResponse(product)
    }

    @ResponseStatus(OK)
    @GetMapping
    @ApiOperation(value = "Listar todos os produtos", tags = ["produto"])
    @ApiResponses(*[
        ApiResponse(code = 200, message = "Sucesso")
    ])
    fun findAll(): List<ProductResponse> {
        val products = productService.findAll()

        return productMapper.convertToResponseList(products)
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar produto", tags = ["produto"])
    @ApiResponses(*[
        ApiResponse(code = 200, message = "Sucesso")
    ])
    fun update(@PathVariable id: UUID, @RequestBody productRequest: CreateProductRequest): ResponseEntity<ProductResponse> {
        val data = productMapper.convertToData(productRequest)
        data.id = id
        val product = productService.update(data);
        return ResponseEntity.ok(productMapper.convertToResponse(product))
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Remover produto por id", tags = ["produto"])
    @ApiResponses(*[
        ApiResponse(code = 204, message = "Sucesso, dado removido"),
        ApiResponse(code = 404, message = "Dado não encontrado")
    ])
    fun delete(@PathVariable id: UUID): ResponseEntity<Void> {
        productService.delete(id)
        return ResponseEntity.noContent().build()
    }
}