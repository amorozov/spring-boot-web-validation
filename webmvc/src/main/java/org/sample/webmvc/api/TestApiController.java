package org.sample.webmvc.api;

import jakarta.annotation.Generated;
import lombok.extern.slf4j.Slf4j;
import org.sample.webmvc.model.TestRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import java.util.UUID;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-06-26T13:21:10.379563688+07:00[Asia/Novosibirsk]")
@Controller
@RequestMapping("${openapi.Sample API with POST.base-path:/v1}")
@Slf4j
public class TestApiController implements org.sample.webmvc.api.TestApi {

    private final NativeWebRequest request;

    @Autowired
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
    // overridden methods must not *alter* parameters annotations. So they either use a whole set
    // of parameters annotations, or use none. That's why @NotNull is omitted
    public ResponseEntity<Void> testParametersVerification(UUID id, String name, Integer age, String description) {
        log.info("Parameters: id={}, name={}, age={}, description={}", id, name, age, description);
        return ResponseEntity.noContent().build();
    }
}
