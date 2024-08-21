package com.springboot.retotecnico.application.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@ToString
@AllArgsConstructor
public enum StatusEnum {
    ACTIVE("1","Active"),
    INACTIVE("0","Inactive");

    private final String cod;
    private final String desc;

    private static final Map<String, StatusEnum> lookup = new LinkedHashMap<>();

    static {
        for (StatusEnum s : EnumSet.allOf(StatusEnum.class))
            lookup.put(s.getCod(), s);
    }
}
