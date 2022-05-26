package com.kastbin.controller


import com.kastbin.dto.PastDTO
import com.kastbin.mapper.PastDTOMapper
import com.kastbin.model.UserDetailsImp
import com.kastbin.repository.PastModelRepo
import com.kastbin.service.PastingService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.*
import java.net.URI

/**
 * Past controller
 *
 * @property pastRepo
 * @property pastMapper
 * @property pastingService
 * @constructor Create empty Past controller
 */
@RestController
@RequestMapping("/paste")
class PastController(
    private val pastRepo: PastModelRepo,
    private val pastMapper: PastDTOMapper,
    private val pastingService: PastingService
) {

    /**
     * acceptPast
     *  triggered by post request
     * @param authUser
     * @param basicUser
     * @param pastDTO
     * Adds a custom header to ResponseEnitity which contains generated URL
     * @return ResponseEntity of HTTP STATUS
     */
    @PostMapping
    fun acceptPast(
        @AuthenticationPrincipal authUser: OAuth2User?,
        @AuthenticationPrincipal basicUser: UserDetailsImp?,
        @RequestBody pastDTO: PastDTO
    ): ResponseEntity<String> {
        if (pastDTO.password == null) return ResponseEntity(HttpStatus.BAD_REQUEST)
        if (pastDTO.password!!.isBlank()) pastDTO.password = null
        val url: String? = pastingService.past(pastDTO, authUser, basicUser)
        if (url !=null)
            return ResponseEntity.status(HttpStatus.OK).location(URI.create("/${url}")).build();
        return  ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    /**
     * getPastBody
     *
     * @param pastURL
     * @return ResponseEntity the past if the giving URL parameter is valid  else Returns Http Not found
     */
    @GetMapping("/{url}")
    fun getPastBody(@PathVariable(value = "url") pastURL: String): ResponseEntity<PastDTO> {
        val past: PastDTO? = pastMapper.toPastDTO(pastRepo.findByPastURL(pastURL))
        if (past != null) return ResponseEntity.accepted().body(past)
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }

}