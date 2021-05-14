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

@RestController
@RequestMapping("/past")
class PastController(
    private val pastRepo: PastModelRepo,
    private val pastMapper: PastDTOMapper,
    private val pastingService: PastingService
) {

    @PostMapping
    fun past(
        @AuthenticationPrincipal authUser: OAuth2User?,
        @AuthenticationPrincipal basicUser: UserDetailsImp?,
        @RequestBody pastDTO: PastDTO
    ): ResponseEntity<HttpStatus> {
        val url: String = pastingService.past(pastDTO, authUser, basicUser)
        val header = HttpHeaders()
        header.location = URI.create("/${url}")
        return ResponseEntity(header, HttpStatus.TEMPORARY_REDIRECT)
    }

    @GetMapping
    fun hello(@AuthenticationPrincipal userImpl: OAuth2User?): ResponseEntity<Void> {
        val header = HttpHeaders()
        header.location = URI.create("/past/something1")
        return ResponseEntity(header, HttpStatus.TEMPORARY_REDIRECT)
    }

    @GetMapping("/{url}")
    fun something(@PathVariable(value = "url") pastURL: String): ResponseEntity<PastDTO> {
        val past: PastDTO? = pastMapper.toPastDTO(pastRepo.findByPastURL(pastURL))
        if (past != null) return ResponseEntity.accepted().body(past)
        return ResponseEntity(HttpStatus.NOT_FOUND)

    }

}