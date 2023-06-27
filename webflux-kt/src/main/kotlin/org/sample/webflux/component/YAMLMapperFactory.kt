package org.sample.webflux.component

import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import jakarta.validation.constraints.NotNull
import org.springframework.stereotype.Component

@Component
class YAMLMapperFactory {
    val yamlMapper: YAMLMapper = YAMLMapper(YAMLFactory())
}
