package com.kastbin.mapper

import com.kastbin.dto.AdminActionDTO
import com.kastbin.model.ActionModel
import org.mapstruct.Mapper

/**
 * @project kastbin
 * @author Rishikesh
 */
@Mapper(componentModel = "Spring")
interface AdminActionDTOMapper {
    fun toActionModel(adminActionDTO: AdminActionDTO): ActionModel
    fun toAdminActionDTO(actionModel: ActionModel): ActionModel
}