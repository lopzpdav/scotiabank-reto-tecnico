package com.springboot.retotecnico.domain.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
public class ErrorResponse {
    private Integer code;
    private String error;
    private List<String> message;
}
