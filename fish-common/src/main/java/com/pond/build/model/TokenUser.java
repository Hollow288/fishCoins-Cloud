package com.pond.build.model;

import lombok.Data;

import java.util.List;

@Data
public class TokenUser {
    private Long userId;

    private List<String> permissions;
}
