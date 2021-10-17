package com.kastbin.repository

import com.kastbin.model.ActionModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * @project kastbin
 * @author Rishikesh
 */
@Repository
interface ActionModelRepo : CrudRepository<ActionModel, Long>