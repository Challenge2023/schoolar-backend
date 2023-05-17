package com.schoolar.schoolarAPI.users.dtos;

import com.schoolar.schoolarAPI.annotations.IsEnum;
import com.schoolar.schoolarAPI.constants.UserTypes;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindByTypeDTO {
    @NotNull(message = "O tipo do usuaŕio deve ser preenchido")
    @IsEnum(enumClass = UserTypes.class, message = "O tipo deve ser um dos sequintes valores: PROFESSOR, STUDENT")
    private UserTypes type;
}
