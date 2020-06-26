package com.stroybiz.cumulative.materials.controllets

import com.stroybiz.cumulative.materials.domain.MainSection
import com.stroybiz.cumulative.materials.services.AssemblyService
import com.stroybiz.cumulative.materials.services.MainSectionService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/mainSections", produces = [MediaType.APPLICATION_JSON_VALUE])
class MainSectionController(private val mainSectionService: MainSectionService,
                            private val assemblyService: AssemblyService) {

    @GetMapping()
    fun getAll(): ResponseEntity<List<MainSection>> = ResponseEntity.ok(mainSectionService.getAll())

    @PostMapping()
    fun create(@RequestBody listMainSection: List<MainSection>): ResponseEntity<List<MainSection>> =
            ResponseEntity.ok(mainSectionService.create(listMainSection))
}