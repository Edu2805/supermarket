package br.com.amorim.supermarket.controller.userdata.dto.requestconfigurationdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserStatusDTO {

    @NotNull(message = "{br.com.supermarket.USER_DATA_FIELD_IS_APPROVED_IS_NOT_EMPTY}")
    @JsonProperty
    private boolean isApproved;
}
