package br.com.amorim.supermarket.configuration.security.roles;

public enum RoleType {

    ADMIN("ADMIN"),
    EMPLOYEE("EMPLOYEE"),
    SECTION_MANAGER("SECTION_MANAGER"),
    DEPARTMENT_MANAGER("DEPARTMENT_MANAGER"),
    MANAGER("MANAGER"),
    BUYER("BUYER"),
    HEAD("HEAD"),
    HR("HR"),
    FINANCE("FINANCE"),
    RECEIPT("RECEIPT");

    public final String role;

    RoleType(String role) {
        this.role = role;
    }
}
