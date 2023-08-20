package br.com.amorim.supermarket.controller.userdata.dto.mapper;

import br.com.amorim.supermarket.configuration.security.roles.RoleType;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;
import static br.com.amorim.supermarket.configuration.security.roles.RoleType.ADMIN;
import static br.com.amorim.supermarket.configuration.security.roles.RoleType.BUYER;
import static br.com.amorim.supermarket.configuration.security.roles.RoleType.DEPARTMENT_MANAGER;
import static br.com.amorim.supermarket.configuration.security.roles.RoleType.EMPLOYEE;
import static br.com.amorim.supermarket.configuration.security.roles.RoleType.FINANCE;
import static br.com.amorim.supermarket.configuration.security.roles.RoleType.HEAD;
import static br.com.amorim.supermarket.configuration.security.roles.RoleType.HR;
import static br.com.amorim.supermarket.configuration.security.roles.RoleType.MANAGER;
import static br.com.amorim.supermarket.configuration.security.roles.RoleType.RECEIPT;
import static br.com.amorim.supermarket.configuration.security.roles.RoleType.SECTION_MANAGER;

@Component
public class RoleTypeMapperImpl implements RoleTypeMapper {

    @Override
    public RoleType mapperRoleType(String role) {
        RoleType roleType = EMPLOYEE;
        if (getString(ADMIN.name()).equals(role)) {
            roleType = ADMIN;
        }
        else if (getString(SECTION_MANAGER.name()).equals(role)) {
            roleType = SECTION_MANAGER;
        }
        else if (getString(DEPARTMENT_MANAGER.name()).equals(role)) {
            roleType = DEPARTMENT_MANAGER;
        }
        else if (getString(MANAGER.name()).equals(role)) {
            roleType = MANAGER;
        }
        else if (getString(BUYER.name()).equals(role)) {
            roleType = BUYER;
        }
        else if (getString(HEAD.name()).equals(role)) {
            roleType = HEAD;
        }
        else if (getString(HR.name()).equals(role)) {
            roleType = HR;
        }
        else if (getString(FINANCE.name()).equals(role)) {
            roleType = FINANCE;
        }
        else if (getString(RECEIPT.name()).equals(role)) {
            roleType = RECEIPT;
        }
        return roleType;
    }
}
