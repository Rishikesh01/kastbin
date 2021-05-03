package com.kastbin.mapper

import com.kastbin.dto.UserRegistrationDTO
import com.kastbin.model.UserModel
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface RegistrationDTOMapper {

    fun toUserRegDTO(userModel: UserModel):UserRegistrationDTO
    fun toUserModel(userRegistrationDTO: UserRegistrationDTO):UserModel
}