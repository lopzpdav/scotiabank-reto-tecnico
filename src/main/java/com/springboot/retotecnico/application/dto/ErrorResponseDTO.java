package com.springboot.retotecnico.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class ErrorResponseDTO {
    private Integer code;
    private String error;
    private List<String> message;
}
