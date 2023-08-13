package br.com.amorim.supermarket.configuration.security.roles;

public enum RoleType {

    ADMIN("{br.com.supermarket.ADMIN}"),
    EMPLOYEE("{br.com.supermarket.EMPLOYEE}"),
    SECTION_MANAGER("{br.com.supermarket.SECTION_MANAGER}"),
    DEPARTMENT_MANAGER("{br.com.supermarket.DEPARTMENT_MANAGER}"),
    MANAGER("{br.com.supermarket.MANAGER}"),
    BUYER("{br.com.supermarket.BUYER}"),
    HEAD("{br.com.supermarket.HEAD}"),
    HR("{br.com.supermarket.HR}"),
    FINANCE("{br.com.supermarket.FINANCE}"),
    RECEIPT("{br.com.supermarket.RECEIPT}");

    public final String role;

    RoleType(String role) {
        this.role = role;
    }
}
