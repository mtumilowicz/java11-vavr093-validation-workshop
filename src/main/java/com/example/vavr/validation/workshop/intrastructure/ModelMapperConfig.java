package com.example.vavr.validation.workshop.intrastructure;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

/**
 * Created by mtumilowicz on 2019-05-09.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModelMapperConfig {
    public static ModelMapper directFieldMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

        return mapper;
    }
}
