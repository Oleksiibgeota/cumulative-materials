package com.stroybiz.cumulative.materials.controllets

import com.stroybiz.cumulative.materials.domain.Assembly
import com.stroybiz.cumulative.materials.services.AssemblyService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
class AssemblyControllers(private  val assemblyService: AssemblyService) {
    @GetMapping("/mainSections/{mainSectionName}/assemblies/{assemblyName}/{elevetion}")
    fun getAssembliesByMainSectionAndElevation(@PathVariable mainSectionName:String,
                                               @PathVariable assemblyName: String,
                                               @PathVariable elevetion: Int):ResponseEntity<Assembly> =
    ResponseEntity.ok(assemblyService.findAssemblyByName(mainSectionName, assemblyName, elevetion))
}