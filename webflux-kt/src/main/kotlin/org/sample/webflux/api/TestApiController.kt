package org.sample.webflux.api


import io.klogging.Klogging
import org.sample.webflux.model.TestRequestBody
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@Validated
@RequestMapping("\${api.base-path:/v1}")
class TestApiController: TestApi, Klogging {
    override suspend fun testBodyVerification(testRequestBody: TestRequestBody): ResponseEntity<Unit> {
        logger.info { "request body is $testRequestBody" }
        return ResponseEntity.noContent().build()
    }

    override suspend fun testParametersVerification(
        id: UUID,
        name: String,
        age: Int,
        description: String?
    ): ResponseEntity<Unit> {
        logger.info("parameters are: id={}, name={}, age={}, description={}", id, name, age, description)
        return ResponseEntity.noContent().build()
    }
}
