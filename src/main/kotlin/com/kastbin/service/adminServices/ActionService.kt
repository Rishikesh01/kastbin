package com.kastbin.service.adminServices

import com.kastbin.dto.AdminActionDTO
import com.kastbin.mapper.AdminActionDTOMapper
import com.kastbin.model.ActionModel
import com.kastbin.model.UserModel
import com.kastbin.repository.ActionModelRepo
import com.kastbin.repository.UserModelRepo
import org.springframework.stereotype.Service

/**
 * @project kastbin
 * @author Rishikesh
 */
@Service
class ActionService(
    private val actionRepo: ActionModelRepo,
    private val userRepo: UserModelRepo,
    private val adminActionDTOMapper: AdminActionDTOMapper
) {
    companion object {
        val BAN: String = "ban"
        val LOCK: String = "lock"
        val UNLOCK: String = "unlock"
    }

    fun takeAction(adminActionDTO: AdminActionDTO, adminName: String): Boolean {
        val action: ActionModel = adminActionDTOMapper.toActionModel(adminActionDTO)
        action.admin = adminName
        actionRepo.save(action)
        val user: UserModel = userRepo.findByEmail(adminActionDTO.userEmail!!) ?: return false
        if (adminActionDTO.action == BAN) {
            user.isEnabled = false
            userRepo.save(user)
        } else if (adminActionDTO.action == LOCK) {
            user.isLocked = false
            userRepo.save(user)
        } else if (adminActionDTO.action == UNLOCK) {
            user.isLocked = true
            userRepo.save(user)
        } else
            return false
        return true
    }

    fun getAction(id: Long): ActionModel? {
        return actionRepo.findById(id).get()
    }
}