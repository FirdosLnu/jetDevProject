package com.jetdev.app.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;


@Data
public class Response {
    private Long status;
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List data;
}
