package com.ramtinmoradiii.learnSpringJava.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "name", "hexValue"})
public class ColorDTO {
    private Long id;
    private String name;
    private String hexValue;
}
