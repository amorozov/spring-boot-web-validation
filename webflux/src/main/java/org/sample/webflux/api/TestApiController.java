package org.sample.webflux.api;

import jakarta.annotation.Generated;
import lombok.extern.slf4j.Slf4j;
import org.sample.webflux.model.TestRequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-06-26T17:38:10.579739940+07:00[Asia/Novosibirsk]")
@Controller
@RequestMapping("${openapi.Sample API with POST.base-path:/v1}")
@Slf4j
public class TestApiController implements TestApi {

    @Override
    public Mono<ResponseEntity<Void>> testBodyVerification(
            Mono<TestRequestBody> testRequestBody, ServerWebExchange exchange
    )  {
        return testRequestBody.map(
                body -> {
                    log.info("Request body is {}", body);
                    return ResponseEntity.noContent().build();
                }
        );
    }

    @Override
    public Mono<ResponseEntity<Void>> testParametersVerification(
            UUID id, String name, Integer age, String description, ServerWebExchange exchange
    )  {
        log.info("parameters are: id={}, name={}, age={}, description={}", id, name, age, description);
        return Mono.just(ResponseEntity.noContent().build());
    }
}
