package com.service.aichi.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("ADMIN:GET"),
    ADMIN_UPDATE("ADMIN:PUT"),
    ADMIN_CREATE("ADMIN:POST"),
    ADMIN_DELETE("ADMIN:DELETE"),
    USER_READ("USER:GET"),
    USER_UPDATE("USER:PUT"),
    USER_CREATE("USER:POST"),
    USER_DELETE("USER:DELETE"),

    ;

    @Getter
    private final String permission;
}
