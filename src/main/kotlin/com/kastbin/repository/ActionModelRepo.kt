package com.kastbin.repository

import com.kastbin.model.ActionModel
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * @project kastbin
 * @author Rishikesh
 */
interface ActionModelRepo : MongoRepository<ActionModel, Long> {
}