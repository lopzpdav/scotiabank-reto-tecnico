package com.springboot.retotecnico.application.mapper;

import com.springboot.retotecnico.application.dto.StudentDTO;
import com.springboot.retotecnico.domain.entities.StudentEntity;
import org.mapstruct.*;

import static org.mapstruct.InjectionStrategy.FIELD;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper (componentModel = SPRING, unmappedTargetPolicy = IGNORE,injectionStrategy = FIELD)
public interface StudentMapper {
    StudentEntity toEntity (StudentDTO studentDto);
    StudentDTO toDto (StudentEntity studentEntity);
}
