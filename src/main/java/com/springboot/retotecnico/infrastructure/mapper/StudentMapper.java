package com.springboot.retotecnico.infrastructure.mapper;

import com.springboot.retotecnico.domain.dto.StudentDto;
import com.springboot.retotecnico.infrastructure.adapter.entities.StudentEntity;
import org.mapstruct.*;

import static org.mapstruct.InjectionStrategy.FIELD;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper (componentModel = SPRING, unmappedTargetPolicy = IGNORE,injectionStrategy = FIELD)
public interface StudentMapper {
    StudentEntity toEntity (StudentDto studentDto);
    StudentDto toDto (StudentEntity studentEntity);
}
