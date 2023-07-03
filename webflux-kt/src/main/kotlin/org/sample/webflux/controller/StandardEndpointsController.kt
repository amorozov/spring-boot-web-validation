package org.sample.webflux.controller

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import org.sample.webflux.component.YAMLMapperFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.http.HttpStatus
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Controller
import org.springframework.util.StreamUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import reactor.core.publisher.Mono
import java.io.IOException
import java.net.URI
import java.nio.charset.Charset

/**
 * Home redirection to OpenAPI api documentation
 */
@Controller
class StandardEndpointsController(
    yamlFactory: YAMLMapperFactory,

    @Value("classpath:/openapi.yaml")
    private val openapi: Resource

) {
    private val yamlMapper: YAMLMapper = yamlFactory.yamlMapper

    @GetMapping(value = ["/"])
    fun redirectToUi(request: ServerHttpRequest, response: ServerHttpResponse): Mono<Void> {
        response.setStatusCode(HttpStatus.FOUND)
        response.headers.location = URI.create("swagger-ui.html")
        return response.setComplete()
    }

    @GetMapping(value = ["/openapi.yaml"], produces = ["application/vnd.oai.openapi"])
    @ResponseBody
    @Throws(IOException::class)
    fun openapiYaml(): String? {
        return openapiContent()
    }

    @GetMapping(value = ["/openapi.json"], produces = ["application/json"])
    @ResponseBody
    @Throws(IOException::class)
    fun openapiJson(): Any? {
        return yamlMapper.readValue(openapiContent(), Any::class.java)
    }

    @Throws(IOException::class)
    private fun openapiContent(): String {
        openapi.inputStream.use { `is` ->
            return StreamUtils.copyToString(
                `is`,
                Charset.defaultCharset()
            )
        }
    }
}
