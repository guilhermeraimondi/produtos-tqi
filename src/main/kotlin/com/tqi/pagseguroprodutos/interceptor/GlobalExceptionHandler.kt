package com.tqi.pagseguroprodutos.interceptor

import com.tqi.pagseguroprodutos.service.exception.NotFoundException
import org.apache.logging.log4j.LogManager
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class GlobalExceptionHandler {
    private val log = LogManager.getLogger()

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(NOT_FOUND)
    fun notFound(e: NotFoundException) {
        log.error("NotFound Exception", e)
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    fun genericError(e: Exception) {
        log.error("Generic Exception", e)
    }
}