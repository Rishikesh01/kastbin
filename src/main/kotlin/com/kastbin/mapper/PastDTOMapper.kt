package com.kastbin.mapper

import com.kastbin.dto.PastDTO
import com.kastbin.model.PastDetailsModel
import org.mapstruct.Mapper

/**
 * Past d t o mapper
 *
 * @constructor Create empty Past d t o mapper
 */
@Mapper(componentModel = "spring")
interface PastDTOMapper {

    /**
     * To past d t o
     *
     * @param pastDetailsModel
     * @return PastDTO
     */

    fun toPastDTO(pastDetailsModel: PastDetailsModel?): PastDTO?

    /**
     * To past details model
     *
     * @param pastDTO
     * @return PastDetails
     */
    fun toPastDetailsModel(pastDTO: PastDTO): PastDetailsModel

    /**
     * To list past details model
     *
     * @param pastDto
     * @return List of PastDetailsModel
     */
    fun toListPastDetailsModel(pastDto:List<PastDTO?>):List<PastDetailsModel>

    /**
     * To list past d t o
     *
     * @param pastDetailsModel
     * @return List of PastDTO
     */
    fun toListPastDTO(pastDetailsModel:List<PastDetailsModel?>):List<PastDTO>
}