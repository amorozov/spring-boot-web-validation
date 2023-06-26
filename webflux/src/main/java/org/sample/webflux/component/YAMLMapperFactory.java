package org.sample.webflux.component;

import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotNull;

@Component
public class YAMLMapperFactory {
    private final YAMLMapper yamlMapper;
    public YAMLMapperFactory() {
        yamlMapper = new YAMLMapper(new YAMLFactory());
    }

    public
    @NotNull YAMLMapper getYAMLMapper() {
        return yamlMapper;
    }
}
