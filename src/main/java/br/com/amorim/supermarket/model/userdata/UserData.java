package br.com.amorim.supermarket.model.userdata;

import br.com.amorim.supermarket.common.enums.RoleType;
import br.com.amorim.supermarket.model.common.CommonIdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "user_data")
public class UserData extends CommonIdEntity {

    @Column(name = "user_name", nullable = false, length = 50)
    @NotEmpty(message = "{br.com.supermarket.USER_DATA_FIELD_USER_NAME_IS_NOT_EMPTY}")
    private String userName;

    @Column(name = "password", nullable = false, length = 8)
    @NotEmpty(message = "{br.com.supermarket.USER_DATA_FIELD_PASSWORD_IS_NOT_EMPTY}")
    private String password;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role", nullable = false)
    @NotNull(message = "{br.com.supermarket.USER_DATA_FIELD_ROLE_IS_NOT_EMPTY}")
    private RoleType role;

    @Column(name = "registration_date", nullable = false)
    private Timestamp registrationDate;

    @Column(name = "is_employee", nullable = false)
    @NotNull(message = "{br.com.supermarket.USER_DATA_FIELD_IS_EMPLOYEE_IS_NOT_EMPTY}")
    private Boolean isEmployee;
}
