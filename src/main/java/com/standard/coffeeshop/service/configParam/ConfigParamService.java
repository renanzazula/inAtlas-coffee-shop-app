package com.standard.coffeeshop.service.configParam;

import com.standard.coffeeshop.service.dto.ConfigParamsEnum;

import java.util.Optional;


public interface ConfigParamService {

    Object getParameterValue(ConfigParamsEnum id);

    <T> Optional<T> getParameterValue(ConfigParamsEnum id, Class<T> clazz);

    <T> Optional<T> getParameterValue(ConfigParamsEnum id, Class<T> clazz, T defaultValue);
}
