package com.kastbin.mapper

import com.kastbin.dto.PastDTO
import com.kastbin.model.PastDetailsModel
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface PastDTOMapper {

    fun toPastDTO(pastDetailsModel: PastDetailsModel?): PastDTO?
    fun toPastDetailsModel(pastDTO: PastDTO): PastDetailsModel
}