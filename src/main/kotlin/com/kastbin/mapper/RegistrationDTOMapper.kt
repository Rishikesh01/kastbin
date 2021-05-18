package com.kastbin.mapper

import com.kastbin.dto.UserRegistrationDTO
import com.kastbin.model.UserModel
import org.mapstruct.Mapper

/**
 * Registration d t o mapper
 *
 * @constructor Create empty Registration d t o mapper
 */
@Mapper(componentModel = "spring")
interface RegistrationDTOMapper {

    /**
     * To user reg d t o
     *
     * @param userModel
     * @return UserRegistrationDTO
     */
    fun toUserRegDTO(userModel: UserModel):UserRegistrationDTO

    /**
     * To user model
     *
     * @param userRegistrationDTO
     * @return UserModel
     */
    fun toUserModel(userRegistrationDTO: UserRegistrationDTO):UserModel
}