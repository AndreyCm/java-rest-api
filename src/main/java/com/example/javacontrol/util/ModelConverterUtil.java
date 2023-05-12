package com.example.javacontrol.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public record ModelConverterUtil(ModelMapper modelMapper) {
    public <T, D> D map(T object, Class<D> targetType){
        return this.modelMapper.map(object, targetType);
    }

    public <T, D> List<D> map(List<T> object, Class<D> targetType){
        return object.stream().map(o -> map(o, targetType)).toList();
    }
}
