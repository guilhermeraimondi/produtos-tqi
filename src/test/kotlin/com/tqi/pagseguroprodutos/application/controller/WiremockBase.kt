package com.tqi.pagseguroprodutos.application.controller

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.MappingBuilder
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.tqi.pagseguroprodutos.application.controller.ProductControllerTest.Companion.createExternalApiResponseOK


object WiremockBase {

    var externalApiMock: WireMockServer = WireMockServer(WireMockConfiguration.options().port(8890))

    fun initServer() {
        start(externalApiMock)
    }

    fun shutdown() {
        externalApiMock.stop()
    }

    fun start(server: WireMockServer) {
        if (!server.isRunning) {
            server.start()
        }
    }

    fun withHappyScenario() {
        withScenario(externalApiMock, ProductControllerTest.createExternalApiResponseOK("scenario"))
    }

    fun withScenario(wireMockServer: WireMockServer, mappingBuilder: MappingBuilder) {
        wireMockServer.resetMappings()
        wireMockServer.stubFor(mappingBuilder)
    }

    fun clearAllScenarios() {
        externalApiMock.resetMappings()
    }
}
