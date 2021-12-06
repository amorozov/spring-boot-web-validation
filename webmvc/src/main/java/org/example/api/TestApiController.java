package org.example.api;

import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.example.model.TestRequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Controller
@Validated
@RequestMapping("${openapi.Sample API with POST.base-path:/v1}")
public class TestApiController implements TestApi {

    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public TestApiController(NativeWebRequest request) {
        this.request = request;
    }

    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> testBodyVerification(TestRequestBody testRequestBody) {
        log.info("body = {}", testRequestBody);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> testParametersVerification(UUID id, String name, Integer age,
            @Size(min = 20) @ApiParam(value = "")
            @Valid @RequestParam(value = "description", required = false)
                    String description
    ) {
        log.info("Parameters: id={}, name={}, age={}, description={}", id, name, age, description);
        return ResponseEntity.noContent().build();
    }
}
