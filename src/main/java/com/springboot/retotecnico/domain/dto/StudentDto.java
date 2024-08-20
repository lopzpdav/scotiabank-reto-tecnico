package com.springboot.retotecnico.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto implements Serializable {
    @NotNull(message = "ID no debe ser nulo")
    @Min(value = 1, message = "ID debe ser mayor o igual que 1")
    private Long id;

    @NotBlank(message = "Nombre no puede estar en blanco o nulo")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Nombre debe contener solo letras")
    private String name;

    @NotBlank(message = "Apellido no puede estar en blanco o nulo")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Apellido debe contener solo letras")
    private String lastName;

    @NotNull(message = "Estado no puede estar en blanco o nulo")
    @Pattern(regexp = "^([01])$", message = "Estado debe ser [1]'Activo' o [0]'Inactivo'")
    private String status;

    @NotNull(message = "Edad no puede ser nula")
    @Min(value = 1, message = "Edad debe ser mayor que 1")
    private Integer age;
}
